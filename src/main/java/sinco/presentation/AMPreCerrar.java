/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.presentation.AMPreCerrar;
/*    */ import sinco.presentation.AMPreCerrarHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AMPreCerrar
/*    */   implements HttpPresentation
/*    */ {
/*    */   private AMPreCerrarHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 32 */     int numero = 0;
/*    */     try {
/* 34 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*    */     }
/* 36 */     catch (Exception e) {
/* 37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*    */     } 
/*    */     
/* 40 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 41 */     SisUsuariosDTO p = pf.cargarRegistro(idNav);
/*    */     
/* 43 */     AreasDAO areaf = new AreasDAO();
/* 44 */     AreasDTO areaProveedor = areaf.getArea(p.getArea());
/* 45 */     areaf.close();
/*    */     
/* 47 */     this.pagHTML = (AMPreCerrarHTML)comms.xmlcFactory.create(AMPreCerrarHTML.class);
/* 48 */     this.pagHTML.setTextNumero("" + numero);
/*    */     
/* 50 */     this.pagHTML.getElementIdNumero().setValue("" + numero);
/* 51 */     this.pagHTML.getElementFuncionario1().setValue("" + p.getNombre());
/* 52 */     this.pagHTML.getElementFecha1().setValue("" + Utilidades.darFormatoFecha(Utilidades.fechaActual()));
/* 53 */     this.pagHTML.getElementCargo1().setValue("" + areaProveedor.getDescripcion());
/*    */     
/* 55 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 56 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMPreCerrar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */