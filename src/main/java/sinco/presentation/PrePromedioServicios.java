/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.w3c.dom.html.HTMLInputElement;
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.presentation.PrePromedioServiciosHTML;
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
/*    */ 
/*    */ 
/*    */ public class PrePromedioServicios
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 44 */     PrePromedioServiciosHTML pagHTML = (PrePromedioServiciosHTML)comms.xmlcFactory.create(PrePromedioServiciosHTML.class);
/*    */     
/* 46 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/* 47 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(idNav);
/*    */     
/* 49 */     AreasDAO rsArea = new AreasDAO();
/* 50 */     AreasDTO regArea = rsArea.getArea(elNavegante.getArea());
/* 51 */     pagHTML.setTextSuArea("" + regArea.getDescripcion());
/*    */     
/* 53 */     HTMLInputElement selElemento = pagHTML.getElementIdMiArea();
/* 54 */     selElemento.setValue("" + regArea.getCodigo());
/*    */     
/* 56 */     HTMLSelectElement selAreas = pagHTML.getElementIdArea();
/*    */     
/* 58 */     HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 59 */     op.setValue("0");
/* 60 */     op.appendChild(pagHTML.createTextNode(" "));
/* 61 */     selAreas.appendChild(op);
/*    */     
/* 63 */     Collection<AreasDTO> arr = rsArea.cargarAreaFrecuente(regArea.getCodigo());
/* 64 */     rsArea.close();
/*    */ 
/*    */     
/* 67 */     boolean hay = false;
/* 68 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 69 */     while (iterator.hasNext()) {
/* 70 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 71 */       hay = true;
/* 72 */       op = (HTMLOptionElement)pagHTML.createElement("option");
/* 73 */       op.setValue("" + reg.getCodigo());
/* 74 */       op.appendChild(pagHTML.createTextNode(reg.getDescripcion()));
/* 75 */       selAreas.appendChild(op);
/*    */     } 
/* 77 */     if (!hay) {
/* 78 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoAreasDisponibles"));
/*    */     }
/*    */     
/* 81 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 82 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrePromedioServicios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */