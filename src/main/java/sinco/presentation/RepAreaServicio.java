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
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.data.AcumuladoMensualDAO;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.presentation.RepAreaServicioHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ public class RepAreaServicio
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 28 */     RepAreaServicioHTML pagHTML = (RepAreaServicioHTML)comms.xmlcFactory.create(RepAreaServicioHTML.class);
/*    */     
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 32 */     int codarea = Integer.parseInt(comms.request.getParameter("area"));
/* 33 */     HTMLInputElement area = pagHTML.getElementIdArea();
/* 34 */     area.setValue(codarea + "");
/* 35 */     HTMLSelectElement servicios = pagHTML.getElementIdServicio();
/* 36 */     ServiciosDAO sf = new ServiciosDAO();
/* 37 */     boolean hay = false;
/* 38 */     Collection arr = sf.cargarTodosDeArea(codarea);
/* 39 */     sf.close();
/*    */     
/* 41 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 42 */     while (iterator.hasNext()) {
/* 43 */       ServiciosDTO serv = (ServiciosDTO)iterator.next();
/* 44 */       HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 45 */       op.setValue("" + serv.getCodigo());
/* 46 */       op.appendChild(pagHTML.createTextNode(serv.getDescripcion()));
/* 47 */       servicios.appendChild(op);
/* 48 */       hay = true;
/*    */     } 
/* 50 */     HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 51 */     op.setValue("-1");
/* 52 */     op.appendChild(pagHTML.createTextNode("todos estos servicios"));
/* 53 */     servicios.appendChild(op);
/*    */     
/* 55 */     if (!hay) {
/* 56 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AreaSinServicios"));
/*    */     }
/* 58 */     HTMLSelectElement annos = pagHTML.getElementIdAnno();
/* 59 */     AcumuladoMensualDAO amf = new AcumuladoMensualDAO();
/* 60 */     amf.cargarTodosAnnos();
/*    */     int anno;
/* 62 */     while ((anno = amf.nextAnno()) != -1) {
/* 63 */       HTMLOptionElement op1 = (HTMLOptionElement)pagHTML.createElement("option");
/* 64 */       op1.setValue(anno + "");
/* 65 */       op1.appendChild(pagHTML.createTextNode(anno + ""));
/* 66 */       annos.appendChild(op1);
/*    */     } 
/* 68 */     amf.close();
/* 69 */     HTMLSelectElement mes1 = pagHTML.getElementIdMes1();
/* 70 */     HTMLSelectElement mes2 = pagHTML.getElementIdMes2();
/*    */     
/* 72 */     String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
/*    */     
/* 74 */     for (int mes = 1; mes <= 12; mes++) {
/* 75 */       HTMLOptionElement op2 = (HTMLOptionElement)pagHTML.createElement("option");
/* 76 */       op2.setValue(mes + "");
/* 77 */       op2.appendChild(pagHTML.createTextNode(meses[mes - 1]));
/* 78 */       mes1.appendChild(op2);
/*    */       
/* 80 */       HTMLOptionElement op3 = (HTMLOptionElement)pagHTML.createElement("option");
/* 81 */       op3.setValue(mes + "");
/* 82 */       op3.appendChild(pagHTML.createTextNode(meses[mes - 1]));
/* 83 */       mes2.appendChild(op3);
/*    */     } 
/* 85 */     HTMLInputElement paramarea = pagHTML.getElementIdArea();
/* 86 */     paramarea.setValue(codarea + "");
/*    */     
/* 88 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 89 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepAreaServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */