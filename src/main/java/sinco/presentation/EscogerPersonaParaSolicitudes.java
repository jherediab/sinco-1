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
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.presentation.EscogerPersonaParaSolicitudes;
/*    */ import sinco.presentation.EscogerPersonaParaSolicitudesHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EscogerPersonaParaSolicitudes
/*    */   implements HttpPresentation
/*    */ {
/*    */   private EscogerPersonaParaSolicitudesHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 30 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 31 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 35 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/* 36 */     SisUsuariosDTO p = rsPersona.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*    */     
/* 38 */     this.pagHTML = (EscogerPersonaParaSolicitudesHTML)comms.xmlcFactory.create(EscogerPersonaParaSolicitudesHTML.class);
/*    */     
/* 40 */     HTMLSelectElement combo = this.pagHTML.getElementIdProveedor();
/* 41 */     llenarCombo(combo, p.getArea(), p.getCodigoEmpleado());
/*    */     
/* 43 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 44 */     comms.response.writeDOM(this.pagHTML);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean llenarCombo(HTMLSelectElement combo, int area, int defecto) {
/* 66 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 67 */     Collection arr = pf.cargarActivoDeArea(area);
/* 68 */     Iterator iterator = arr.iterator();
/* 69 */     while (iterator.hasNext()) {
/* 70 */       SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
/* 71 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 72 */       op.setValue("" + personaDelArea.getCodigoEmpleado());
/* 73 */       op.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres()));
/* 74 */       if (defecto == personaDelArea.getCodigoEmpleado()) {
/* 75 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 76 */         escogida.setValue("on");
/* 77 */         op.setAttributeNode(escogida);
/*    */       } 
/* 79 */       combo.appendChild(op);
/*    */     } 
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerPersonaParaSolicitudes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */