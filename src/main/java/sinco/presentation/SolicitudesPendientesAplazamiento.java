/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.html.HTMLElement;
/*    */ import org.w3c.dom.html.HTMLTableElement;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.presentation.SolicitudesPendientesAplazamiento;
/*    */ import sinco.presentation.SolicitudesPendientesAplazamientoHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class SolicitudesPendientesAplazamiento
/*    */   implements HttpPresentation {
/*    */   private SolicitudesPendientesAplazamientoHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 31 */     this.pagHTML = (SolicitudesPendientesAplazamientoHTML)comms.xmlcFactory.create(SolicitudesPendientesAplazamientoHTML.class);
/* 32 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/* 33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 35 */     VSolicitudesDAO rsLibre = new VSolicitudesDAO();
/* 36 */     Collection arr = rsLibre.pendientesAplazamiento(idNav);
/* 37 */     rsLibre.close();
/*    */     
/* 39 */     boolean fondo = true;
/* 40 */     int cuantas = 0;
/* 41 */     Iterator iterator = arr.iterator();
/* 42 */     while (iterator.hasNext()) {
/* 43 */       VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
/*    */       
/* 45 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 46 */       fondo = !fondo;
/* 47 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*    */       
/* 49 */       eltr.appendChild(newtd("" + reg.getNumero()));
/*    */       
/* 51 */       String sPagina = "VSEnCurso.po?solicitud=" + reg.getNumero();
/* 52 */       eltr.appendChild(newtdhref(reg.getNombreServicio(), sPagina));
/* 53 */       eltr.appendChild(newtd("" + reg.getNombreAreaProveedora()));
/* 54 */       eltr.appendChild(newtd("" + reg.getNombreProveedor()));
/* 55 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaEstimadaTerminacion())));
/* 56 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaGenerada())));
/* 57 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getObservaciones())));
/*    */       
/* 59 */       hte.appendChild(eltr);
/* 60 */       cuantas++;
/*    */     } 
/* 62 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 63 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 64 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */   
/*    */   private Attr newAttr(String tipo, String valor) {
/* 68 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 69 */     atrib.setValue(valor);
/* 70 */     return atrib;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private HTMLElement newtd(String contenido) {
/* 84 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 85 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 86 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 87 */     return td;
/*    */   }
/*    */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 90 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 91 */     Element enlace = this.pagHTML.createElement("a");
/* 92 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 93 */     enlace.appendChild(hijo);
/* 94 */     Attr donde = this.pagHTML.createAttribute("href");
/* 95 */     donde.setValue(vinculo);
/* 96 */     enlace.setAttributeNode(donde);
/* 97 */     td.appendChild(enlace);
/* 98 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 99 */     return td;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesPendientesAplazamiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */