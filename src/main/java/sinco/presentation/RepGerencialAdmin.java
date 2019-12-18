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
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.presentation.RepGerencialAdmin;
/*    */ import sinco.presentation.RepGerencialAdminHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class RepGerencialAdmin
/*    */   implements HttpPresentation {
/*    */   private RepGerencialAdminHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 31 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/* 32 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/* 33 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*    */     
/* 35 */     String sCondiciones = "&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2;
/*    */     
/* 37 */     this.pagHTML = (RepGerencialAdminHTML)comms.xmlcFactory.create(RepGerencialAdminHTML.class);
/*    */     
/* 39 */     HTMLTableElement laTabla = this.pagHTML.getElementDetalle();
/*    */     
/* 41 */     AreasDAO rsAreas = new AreasDAO();
/* 42 */     Collection<AreasDTO> arr = rsAreas.cargarLasDeArriba();
/* 43 */     rsAreas.close();
/*    */     
/* 45 */     boolean fondo = false;
/* 46 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 47 */     while (iterator.hasNext()) {
/* 48 */       AreasDTO regArea = (AreasDTO)iterator.next();
/* 49 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*    */       
/* 51 */       fondo = !fondo;
/* 52 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*    */       
/* 54 */       String sPagina = "RepGerencial.po?area=" + regArea.getCodigo() + sCondiciones;
/* 55 */       eltr.appendChild(newtdhref("" + regArea.getDescripcion(), sPagina));
/* 56 */       laTabla.appendChild(eltr);
/*    */     } 
/*    */     
/* 59 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 60 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 66 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 67 */     Element enlace = this.pagHTML.createElement("a");
/* 68 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 69 */     enlace.appendChild(hijo);
/* 70 */     Attr donde = this.pagHTML.createAttribute("href");
/* 71 */     donde.setValue(vinculo);
/* 72 */     enlace.setAttributeNode(donde);
/* 73 */     td.appendChild(enlace);
/* 74 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 75 */     return td;
/*    */   }
/*    */   private Attr newAttr(String tipo, String valor) {
/* 78 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 79 */     atrib.setValue(valor);
/* 80 */     return atrib;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepGerencialAdmin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */