/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.data.SeguridadDAO;
/*    */ import sinco.presentation.EscogerAreaParaSolicitudesHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EscogerAreaParaSolicitudes
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 31 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*    */     
/* 33 */     EscogerAreaParaSolicitudesHTML pagHTML = (EscogerAreaParaSolicitudesHTML)comms.xmlcFactory.create(EscogerAreaParaSolicitudesHTML.class);
/*    */     
/* 35 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 36 */     if (!rsSeguridad.getEstadoConexion()) {
/* 37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*    */     }
/* 39 */     boolean permiso = rsSeguridad.tieneLlave(miGrupo, "oVerAreasACargo");
/* 40 */     boolean bMostrarTodasLasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/* 41 */     rsSeguridad.close();
/*    */     
/* 43 */     HTMLSelectElement areas = pagHTML.getElementIdArea();
/*    */     
/* 45 */     Collection arr = null;
/* 46 */     AreasDAO af = new AreasDAO();
/* 47 */     if (bMostrarTodasLasAreas) {
/* 48 */       arr = af.cargarActivas();
/*    */     }
/* 50 */     else if (permiso) {
/* 51 */       arr = af.cargarAreasHijas(idNav);
/*    */     } else {
/*    */       
/* 54 */       arr = af.cargarMisAreas(idNav);
/*    */     } 
/* 56 */     af.close();
/*    */     
/* 58 */     Iterator iterator = arr.iterator();
/* 59 */     while (iterator.hasNext()) {
/* 60 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 61 */       HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 62 */       op.setValue("" + reg.getCodigo());
/* 63 */       op.appendChild(pagHTML.createTextNode(reg.getDescripcion()));
/* 64 */       areas.appendChild(op);
/*    */     } 
/* 66 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 67 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerAreaParaSolicitudes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */