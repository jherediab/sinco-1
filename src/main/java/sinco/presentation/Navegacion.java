/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.presentation.NavegacionHTML;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Navegacion
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 29 */     NavegacionHTML pagHTML = (NavegacionHTML)comms.xmlcFactory.create(NavegacionHTML.class);
/* 30 */     int idNav = 0;
/*    */     try {
/* 32 */       idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 33 */       pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*    */       
/* 35 */       SisUsuariosDAO reg = new SisUsuariosDAO();
/* 36 */       SisUsuariosDTO registro = reg.cargarRegistro(idNav);
/* 37 */       pagHTML.setTextUsuario(registro.getIdcorreo());
/* 38 */       pagHTML.setTextNombres(registro.getNombres());
/* 39 */       pagHTML.setTextApellidos(registro.getApellidos());
/* 40 */       pagHTML.setTextFecha(Utilidades.getDiaActual() + " - " + Utilidades.nombreMes(Utilidades.getMesActual()) + " - " + Utilidades.getAnnoActual());
/*    */     }
/* 42 */     catch (Exception e) {}
/*    */     
/* 44 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Navegacion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */