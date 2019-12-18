/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.EscogerServicioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class EscogerServicio
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  28 */     int laArea = Integer.parseInt(comms.request.getParameter("area"));
/*  29 */     if (laArea == 0) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     }
/*     */     
/*  33 */     int laGemela = -1;
/*     */     try {
/*  35 */       laGemela = Integer.parseInt(comms.request.getParameter("gemela"));
/*     */     }
/*  37 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  40 */     int elPadre = -1;
/*     */     try {
/*  42 */       elPadre = Integer.parseInt(comms.request.getParameter("padre"));
/*     */     }
/*  44 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  47 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  49 */     EscogerServicioHTML pagHTML = (EscogerServicioHTML)comms.xmlcFactory.create(EscogerServicioHTML.class);
/*     */ 
/*     */ 
/*     */     
/*  53 */     HTMLFormElement forma = pagHTML.getElementForma();
/*  54 */     forma.setAction("EscogerPersona.po");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     HTMLSelectElement servicios = pagHTML.getElementServicio();
/*     */     
/*  61 */     HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/*  62 */     op.setValue("0");
/*  63 */     op.appendChild(pagHTML.createTextNode(" "));
/*  64 */     servicios.appendChild(op);
/*     */     
/*  66 */     ServiciosDAO sf = new ServiciosDAO();
/*  67 */     boolean hay = false;
/*  68 */     Collection<ServiciosDTO> arr = sf.cargarTodosDeArea(laArea, idNav);
/*  69 */     sf.close();
/*     */     
/*  71 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/*  72 */     while (iterator.hasNext()) {
/*  73 */       ServiciosDTO serv = (ServiciosDTO)iterator.next();
/*  74 */       op = (HTMLOptionElement)pagHTML.createElement("option");
/*  75 */       op.setValue("" + serv.getCodigo());
/*  76 */       op.appendChild(pagHTML.createTextNode(serv.getDescripcion()));
/*  77 */       servicios.appendChild(op);
/*  78 */       hay = true;
/*     */     } 
/*     */     
/*  81 */     if (!hay) {
/*  82 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AreaSinServicios"));
/*     */     }
/*     */     
/*  85 */     if (laArea > 0) {
/*  86 */       HTMLInputElement temp = pagHTML.getElementIdArea();
/*  87 */       temp.setValue("" + laArea);
/*     */     } 
/*     */     
/*  90 */     if (laGemela > 0) {
/*  91 */       HTMLInputElement temp = pagHTML.getElementIdGemela();
/*  92 */       temp.setValue("" + laGemela);
/*     */     } 
/*     */     
/*  95 */     if (elPadre > 0) {
/*  96 */       HTMLInputElement temp = pagHTML.getElementIdPadre();
/*  97 */       temp.setValue("" + elPadre);
/*     */     } 
/*     */     
/* 100 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 101 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */