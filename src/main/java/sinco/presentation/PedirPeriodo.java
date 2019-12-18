/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.html.HTMLFormElement;
/*    */ import org.w3c.dom.html.HTMLInputElement;
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.presentation.PedirPeriodo;
/*    */ import sinco.presentation.PedirPeriodoHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PedirPeriodo
/*    */   implements HttpPresentation
/*    */ {
/*    */   private PedirPeriodoHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 37 */     this.pagHTML = (PedirPeriodoHTML)comms.xmlcFactory.create(PedirPeriodoHTML.class);
/*    */     
/* 39 */     String pagina = comms.request.getParameter("pagina");
/*    */     
/* 41 */     HTMLSelectElement annos = this.pagHTML.getElementIdAnno();
/* 42 */     for (int anno = 2000; anno <= Utilidades.getAnnoActual(); anno++) {
/* 43 */       HTMLOptionElement op1 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 44 */       op1.setValue(anno + "");
/* 45 */       op1.appendChild(this.pagHTML.createTextNode("" + anno));
/* 46 */       annos.appendChild(op1);
/* 47 */       if (anno == Utilidades.getAnnoActual()) {
/* 48 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 49 */         escogida.setValue("on");
/* 50 */         op1.setAttributeNode(escogida);
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     llenarComboMeses(this.pagHTML.getElementIdMes(), Utilidades.getMesActual());
/*    */     
/* 56 */     HTMLFormElement forma = this.pagHTML.getElementForma();
/* 57 */     forma.setAction(pagina);
/*    */     
/* 59 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 61 */     SisUsuariosDAO pfactory = new SisUsuariosDAO();
/* 62 */     SisUsuariosDTO experto = pfactory.cargarRegistro(idNav);
/*    */     
/* 64 */     HTMLInputElement idhidden = this.pagHTML.getElementIdArea();
/* 65 */     idhidden.setValue("" + experto.getArea());
/*    */     
/* 67 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 68 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void llenarComboMeses(HTMLSelectElement combo, int mesdefecto) {
/* 79 */     for (int i = 1; i <= 12; i++) {
/* 80 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 81 */       op.setValue("" + i);
/* 82 */       op.appendChild(this.pagHTML.createTextNode(Utilidades.nombreMes(i)));
/* 83 */       if (i == mesdefecto) {
/* 84 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 85 */         escogida.setValue("on");
/* 86 */         op.setAttributeNode(escogida);
/*    */       } 
/* 88 */       combo.appendChild(op);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirPeriodo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */