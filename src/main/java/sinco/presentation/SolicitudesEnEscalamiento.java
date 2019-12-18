/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.html.HTMLElement;
/*    */ import org.w3c.dom.html.HTMLTableSectionElement;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.presentation.SolicitudesEnEscalamiento;
/*    */ import sinco.presentation.SolicitudesPorAtenderHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class SolicitudesEnEscalamiento
/*    */   implements HttpPresentation {
/*    */   private SolicitudesPorAtenderHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 28 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 30 */     this.pagHTML = (SolicitudesPorAtenderHTML)comms.xmlcFactory.create(SolicitudesPorAtenderHTML.class);
/*    */     
/* 32 */     int persona = Integer.parseInt(comms.request.getParameter("Persona"));
/*    */     
/* 34 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/* 35 */     rsSol.cargarEnEscalamientoPedidasA(persona);
/* 36 */     VSolicitudesDTO regSol = rsSol.next();
/*    */     
/* 38 */     boolean fondo = true;
/* 39 */     int cuantas = 0;
/* 40 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 41 */     while (regSol != null) {
/* 42 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*    */       
/* 44 */       fondo = !fondo;
/* 45 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*    */       
/* 47 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/* 48 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*    */       
/* 50 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/* 51 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/* 52 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/* 53 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*    */       
/* 55 */       hte.appendChild(eltr);
/* 56 */       regSol = rsSol.next();
/* 57 */       cuantas++;
/*    */     } 
/* 59 */     rsSol.close();
/* 60 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 61 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 62 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */   
/*    */   private Attr newAttr(String tipo, String valor) {
/* 67 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 68 */     atrib.setValue(valor);
/* 69 */     return atrib;
/*    */   }
/*    */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 72 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 73 */     Element enlace = this.pagHTML.createElement("a");
/* 74 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 75 */     enlace.appendChild(hijo);
/* 76 */     Attr donde = this.pagHTML.createAttribute("href");
/* 77 */     donde.setValue(vinculo);
/* 78 */     enlace.setAttributeNode(donde);
/* 79 */     td.appendChild(enlace);
/* 80 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 81 */     return td;
/*    */   }
/*    */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 84 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 85 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 86 */     if (alinear) {
/* 87 */       td.setAttributeNode(newAttr("align", "right"));
/*    */     }
/* 89 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 90 */     return td;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesEnEscalamiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */