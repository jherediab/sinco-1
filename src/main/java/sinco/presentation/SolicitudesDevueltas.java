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
/*    */ import org.w3c.dom.html.HTMLTableElement;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.presentation.SolicitudesDevueltas;
/*    */ import sinco.presentation.SolicitudesDevueltasHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class SolicitudesDevueltas implements HttpPresentation {
/*    */   private SolicitudesDevueltasHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 28 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 30 */     this.pagHTML = (SolicitudesDevueltasHTML)comms.xmlcFactory.create(SolicitudesDevueltasHTML.class);
/* 31 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/* 32 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/* 33 */     sf.cargarTodosDevueltasPedidasPor(idNav, 1);
/* 34 */     VSolicitudesDTO regSol = sf.next();
/*    */     
/* 36 */     int cuantas = 0;
/* 37 */     boolean fondo = true;
/* 38 */     while (regSol != null) {
/* 39 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*    */       
/* 41 */       fondo = !fondo;
/* 42 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*    */       
/* 44 */       eltr.appendChild(newtd("" + regSol.getNumero(), false));
/* 45 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero();
/* 46 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/* 47 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/* 48 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/* 49 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaGenerada()), false));
/*    */       
/* 51 */       hte.appendChild(eltr);
/* 52 */       regSol = sf.next();
/* 53 */       cuantas++;
/*    */     } 
/* 55 */     sf.close();
/* 56 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 57 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 58 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */   
/*    */   private Attr newAttr(String tipo, String valor) {
/* 63 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 64 */     atrib.setValue(valor);
/* 65 */     return atrib;
/*    */   }
/*    */   
/*    */   private Element newhref(String descripcion, String url) {
/* 69 */     Element enlace = this.pagHTML.createElement("a");
/* 70 */     Node hijo = this.pagHTML.createTextNode(descripcion);
/* 71 */     enlace.appendChild(hijo);
/* 72 */     Attr atrib = this.pagHTML.createAttribute("href");
/* 73 */     atrib.setValue(url);
/* 74 */     enlace.setAttributeNode(atrib);
/* 75 */     return enlace;
/*    */   }
/*    */   
/*    */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 79 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 80 */     Element enlace = this.pagHTML.createElement("a");
/* 81 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 82 */     enlace.appendChild(hijo);
/* 83 */     Attr donde = this.pagHTML.createAttribute("href");
/* 84 */     donde.setValue(vinculo);
/* 85 */     enlace.setAttributeNode(donde);
/* 86 */     td.appendChild(enlace);
/* 87 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 88 */     return td;
/*    */   }
/*    */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 91 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 92 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 93 */     if (alinear) {
/* 94 */       td.setAttributeNode(newAttr("align", "right"));
/*    */     }
/* 96 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 97 */     return td;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesDevueltas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */