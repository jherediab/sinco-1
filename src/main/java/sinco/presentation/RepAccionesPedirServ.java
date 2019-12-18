/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.w3c.dom.html.HTMLFormElement;
/*    */ import org.w3c.dom.html.HTMLInputElement;
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.FechaDTO;
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.presentation.RepAccionesPedirServHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepAccionesPedirServ
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 32 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/* 33 */     String fechaFin = comms.request.getParameter("fechaFin");
/* 34 */     int rol = Integer.parseInt(comms.request.getParameter("rol"));
/* 35 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/* 36 */     int accion = Integer.parseInt(comms.request.getParameter("accion"));
/*    */     
/* 38 */     if (fechaInicio != null && !fechaInicio.equals("") && 
/* 39 */       !Utilidades.validarFormatoFecha(fechaInicio)) {
/* 40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=FechaInicio"));
/*    */     }
/*    */ 
/*    */     
/* 44 */     if (fechaFin != null && !fechaFin.equals("") && 
/* 45 */       !Utilidades.validarFormatoFecha(fechaFin)) {
/* 46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fechaFin"));
/*    */     }
/*    */ 
/*    */     
/* 50 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/* 51 */     miFecha.fechaMasDias(1L);
/* 52 */     fechaFin = miFecha.getFecha();
/*    */     
/* 54 */     RepAccionesPedirServHTML pagHTML = (RepAccionesPedirServHTML)comms.xmlcFactory.create(RepAccionesPedirServHTML.class);
/*    */     
/* 56 */     HTMLInputElement idFecha = pagHTML.getElementIdFechaInicio();
/* 57 */     idFecha.setValue("" + fechaInicio);
/*    */     
/* 59 */     idFecha = pagHTML.getElementIdFechaFin();
/* 60 */     idFecha.setValue("" + fechaFin);
/*    */     
/* 62 */     idFecha = pagHTML.getElementIdRol();
/* 63 */     idFecha.setValue("" + rol);
/*    */     
/* 65 */     idFecha = pagHTML.getElementIdArea();
/* 66 */     idFecha.setValue("" + area);
/*    */     
/* 68 */     HTMLFormElement forma = pagHTML.getElementForma();
/* 69 */     forma.setAction((accion == 1) ? "SolicitudesDeArea.po" : "RepCar01F1.po");
/*    */     
/* 71 */     HTMLSelectElement servicios = pagHTML.getElementServicio();
/*    */     
/* 73 */     boolean hay = false;
/*    */     
/* 75 */     ServiciosDAO sf = new ServiciosDAO();
/* 76 */     Collection<ServiciosDTO> arr = sf.cargarHechos(area, rol, fechaInicio, Utilidades.siguienteDia(fechaFin));
/* 77 */     sf.close();
/*    */     
/* 79 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 80 */     while (iterator.hasNext()) {
/* 81 */       ServiciosDTO serv = (ServiciosDTO)iterator.next();
/*    */       
/* 83 */       HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 84 */       op.setValue("" + serv.getCodigo());
/* 85 */       op.appendChild(pagHTML.createTextNode(serv.getDescripcion()));
/* 86 */       servicios.appendChild(op);
/* 87 */       hay = true;
/*    */     } 
/*    */     
/* 90 */     if (!hay) {
/* 91 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AreaSinServicios"));
/*    */     }
/* 93 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 94 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepAccionesPedirServ.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */