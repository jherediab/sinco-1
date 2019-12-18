/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.html.HTMLFormElement;
/*    */ import org.w3c.dom.html.HTMLOptionElement;
/*    */ import org.w3c.dom.html.HTMLSelectElement;
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.business.FechaDTO;
/*    */ import sinco.business.ParametrosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.presentation.EscogerAreaHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EscogerAreaTodas
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 30 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 31 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 34 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 36 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/*    */     
/* 38 */     AreasDAO af = new AreasDAO();
/* 39 */     EscogerAreaHTML pagHTML = (EscogerAreaHTML)comms.xmlcFactory.create(EscogerAreaHTML.class);
/*    */     
/* 41 */     HTMLFormElement forma = pagHTML.getElementFormaFiltro();
/* 42 */     forma.setAction("EscogerAreaTodas.po");
/*    */ 
/*    */     
/* 45 */     int tipo = 0;
/* 46 */     String filtro = "";
/*    */     try {
/* 48 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/* 49 */       if (tipo == 1) {
/* 50 */         filtro = comms.request.getParameter("filtro");
/*    */       }
/*    */     }
/* 53 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 56 */     if (tipo == 1) {
/* 57 */       arr = af.cargarTodosAbiertos(filtro, 0);
/*    */     } else {
/*    */       
/* 60 */       FechaDTO miFecha = new FechaDTO(Utilidades.fechaActual());
/* 61 */       miFecha.fechaMasDias(15L);
/* 62 */       String fechaH = miFecha.getFecha();
/* 63 */       miFecha.fechaMasDias((-15 - ParametrosDTO.getInt("numero.dias.retorno")));
/* 64 */       arr = af.cargarFrecuentes(idNav, 0, miFecha.getFecha(), fechaH);
/*    */     } 
/* 66 */     af.close();
/*    */ 
/*    */     
/* 69 */     HTMLSelectElement areas = pagHTML.getElementIdarea();
/* 70 */     boolean hay = false;
/* 71 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 72 */     while (iterator.hasNext()) {
/* 73 */       AreasDTO area = (AreasDTO)iterator.next();
/* 74 */       hay = true;
/* 75 */       HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 76 */       op.setValue("" + area.getCodigo());
/* 77 */       op.appendChild(pagHTML.createTextNode(area.getDescripcion()));
/* 78 */       areas.appendChild(op);
/*    */     } 
/*    */     
/* 81 */     if (!hay) {
/* 82 */       Element divPadre = pagHTML.getElementSeleccion();
/* 83 */       divPadre.getParentNode().removeChild(divPadre);
/*    */     } 
/*    */     
/* 86 */     HTMLFormElement forma2 = pagHTML.getElementForma();
/* 87 */     forma2.setAction("ServiciosDeArea.po");
/* 88 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 89 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerAreaTodas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */