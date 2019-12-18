/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AcumuladosPersonaDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AcumuladosPersonaDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.presentation.RepGerencialPersonas;
/*     */ import sinco.presentation.RepGerencialPersonasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepGerencialPersonas implements HttpPresentation {
/*     */   private RepGerencialPersonasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  27 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  28 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  29 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  30 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     
/*  32 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     
/*  34 */     this.pagHTML = (RepGerencialPersonasHTML)comms.xmlcFactory.create(RepGerencialPersonasHTML.class);
/*     */     
/*  36 */     AreasDAO rsArea = new AreasDAO();
/*  37 */     AreasDTO regArea = rsArea.getArea(area);
/*  38 */     rsArea.close();
/*     */     
/*  40 */     this.pagHTML.setTextNombreArea(regArea.getDescripcion());
/*     */     
/*  42 */     HTMLTableElement laTabla = this.pagHTML.getElementDetalle();
/*     */     
/*  44 */     AcumuladosPersonaDAO rsAcumuladosPer = new AcumuladosPersonaDAO();
/*  45 */     rsAcumuladosPer.cargarAcumulados(anno, mes1, mes2, area);
/*  46 */     AcumuladosPersonaDTO reg = rsAcumuladosPer.next();
/*  47 */     boolean fondo = true;
/*     */     
/*  49 */     while (reg != null) {
/*  50 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  51 */       fondo = !fondo;
/*  52 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  54 */       eltr.appendChild(newtd("" + reg.getNombre(), false));
/*     */       
/*  56 */       eltr.appendChild(newtd("" + reg.getSolicitudesRecibidas(), true));
/*  57 */       eltr.appendChild(newtd("" + reg.getSolicitudesAtendidas(), true));
/*  58 */       eltr.appendChild(newtd("" + reg.getSolicitudesEscaladas(), true));
/*     */       
/*  60 */       if (reg.getIndiceOportunidad() == 0.0F) {
/*  61 */         eltr.appendChild(newtd("N/A", false));
/*     */       } else {
/*     */         
/*  64 */         eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2((reg.getIndiceOportunidad() * 100.0F)), 2) + "%", false));
/*     */       } 
/*     */       
/*  67 */       if (reg.getIndiceConfiabilidad() == 0.0F) {
/*  68 */         eltr.appendChild(newtd("N/A", false));
/*     */       } else {
/*     */         
/*  71 */         eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2((reg.getIndiceConfiabilidad() * 100.0F)), 2) + "%", false));
/*     */       } 
/*     */       
/*  74 */       if (reg.getIndiceTotal() == 0.0F) {
/*  75 */         eltr.appendChild(newtd("N/A", false));
/*     */       } else {
/*     */         
/*  78 */         eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(reg.getIndiceTotal()), 2) + "%", false));
/*     */       } 
/*     */       
/*  81 */       laTabla.appendChild(eltr);
/*     */       
/*  83 */       reg = rsAcumuladosPer.next();
/*     */     } 
/*  85 */     rsAcumuladosPer.close();
/*  86 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  87 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/*  92 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  93 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  94 */     if (alinear) {
/*  95 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/*  97 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  98 */     return td;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 102 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 103 */     atrib.setValue(valor);
/* 104 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepGerencialPersonas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */