/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.html.HTMLFormElement;
/*    */ import org.w3c.dom.html.HTMLInputElement;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.presentation.PedirFechasHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ import sinco.spec.Utilidades2;
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
/*    */ public class PedirFechasDos
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 31 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 33 */     int area = 0;
/*    */     try {
/* 35 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*    */     }
/* 37 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 40 */     int persona = 0;
/*    */     try {
/* 42 */       persona = Integer.parseInt(comms.request.getParameter("persona"));
/*    */     }
/* 44 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 47 */     int tipo = 0;
/*    */     try {
/* 49 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*    */     }
/* 51 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 54 */     int plan = 0;
/*    */     try {
/* 56 */       plan = Integer.parseInt(comms.request.getParameter("plan"));
/*    */     }
/* 58 */     catch (Exception e) {}
/*    */ 
/*    */ 
/*    */     
/* 62 */     PedirFechasHTML pagHTML = (PedirFechasHTML)comms.xmlcFactory.create(PedirFechasHTML.class);
/*    */     
/* 64 */     String pagina = comms.request.getParameter("pagina");
/*    */     
/* 66 */     HTMLInputElement idFecha = pagHTML.getElementIdFechaInicio();
/* 67 */     idFecha.setValue("" + Utilidades.getAnnoActual() + "-" + Utilidades.formato(Utilidades.getMesActual(), 2) + "-01");
/*    */     
/* 69 */     idFecha = pagHTML.getElementIdFechaFin();
/* 70 */     idFecha.setValue("" + Utilidades.darFormatoFecha(Utilidades2.diaSiguiente()));
/*    */     
/* 72 */     HTMLFormElement forma = pagHTML.getElementForma();
/* 73 */     forma.setAction(pagina);
/*    */     
/* 75 */     HTMLInputElement idDatos = pagHTML.getElementIdTipo();
/* 76 */     idDatos.setValue("" + tipo);
/*    */     
/* 78 */     idDatos = pagHTML.getElementIdArea();
/* 79 */     idDatos.setValue("" + area);
/*    */     
/* 81 */     idDatos = pagHTML.getElementIdPersona();
/* 82 */     idDatos.setValue("" + persona);
/*    */     
/* 84 */     idDatos = pagHTML.getElementIdPlan();
/* 85 */     idDatos.setValue("" + plan);
/*    */     
/* 87 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 88 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirFechasDos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */