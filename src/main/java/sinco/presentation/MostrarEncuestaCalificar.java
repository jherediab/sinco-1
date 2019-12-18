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
/*    */ import sinco.business.EncuestaDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.EncuestaDAO;
/*    */ import sinco.presentation.MostrarEncuestaCalificar;
/*    */ import sinco.presentation.MostrarEncuestaCalificarHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MostrarEncuestaCalificar
/*    */   implements HttpPresentation
/*    */ {
/*    */   private MostrarEncuestaCalificarHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 38 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 39 */     this.pagHTML = (MostrarEncuestaCalificarHTML)comms.xmlcFactory.create(MostrarEncuestaCalificarHTML.class);
/*    */     
/* 41 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/* 42 */     if (!rsEncuesta.cargarPendientesCalificacion(idNav)) {
/* 43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=MostrarEncuetasCalificar"));
/*    */     }
/*    */     
/* 46 */     EncuestaDTO regEncuesta = rsEncuesta.next();
/* 47 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 48 */     boolean fondo = true;
/* 49 */     int cuantas = 0;
/* 50 */     while (regEncuesta != null) {
/* 51 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*    */       
/* 53 */       fondo = !fondo;
/* 54 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*    */       
/* 56 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regEncuesta.getFecha()), false));
/* 57 */       eltr.appendChild(newtd("" + regEncuesta.getDescripcion(), false));
/* 58 */       String sPagina = "PreCalificarEncuesta.po?numero=" + regEncuesta.getNumero();
/* 59 */       eltr.appendChild(newtdhref("" + regEncuesta.getNombreServicio(), sPagina));
/*    */       
/* 61 */       hte.appendChild(eltr);
/* 62 */       regEncuesta = rsEncuesta.next();
/* 63 */       cuantas++;
/*    */     } 
/* 65 */     rsEncuesta.close();
/* 66 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 67 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 68 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */   
/*    */   private Attr newAttr(String tipo, String valor) {
/* 73 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 74 */     atrib.setValue(valor);
/* 75 */     return atrib;
/*    */   }
/*    */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 78 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 79 */     Element enlace = this.pagHTML.createElement("a");
/* 80 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 81 */     enlace.appendChild(hijo);
/* 82 */     Attr donde = this.pagHTML.createAttribute("href");
/* 83 */     donde.setValue(vinculo);
/* 84 */     enlace.setAttributeNode(donde);
/* 85 */     td.appendChild(enlace);
/* 86 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 87 */     return td;
/*    */   }
/*    */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 90 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 91 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 92 */     if (alinear) {
/* 93 */       td.setAttributeNode(newAttr("align", "right"));
/*    */     }
/* 95 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 96 */     return td;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\MostrarEncuestaCalificar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */