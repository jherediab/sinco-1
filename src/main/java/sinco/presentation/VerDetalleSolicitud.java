/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SolicitudDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.presentation.VerDetalleSolicitud;
/*     */ import sinco.presentation.VerDetalleSolicitudHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class VerDetalleSolicitud
/*     */   implements HttpPresentation
/*     */ {
/*     */   private VerDetalleSolicitudHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  38 */     SolicitudDAO sf = new SolicitudDAO();
/*  39 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  40 */     SolicitudDTO solicitud = sf.getSolicitud(idsol);
/*  41 */     sf.close();
/*     */     
/*  43 */     if (solicitud == null) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudAnulada"));
/*     */     }
/*  46 */     ServiciosDAO serf = new ServiciosDAO();
/*  47 */     ServiciosDTO servicio = serf.cargarRegistro(solicitud.getCodigoServicio());
/*  48 */     serf.close();
/*     */     
/*  50 */     this.pagHTML = (VerDetalleSolicitudHTML)comms.xmlcFactory.create(VerDetalleSolicitudHTML.class);
/*  51 */     this.pagHTML.setTextServicio(servicio.getDescripcion());
/*     */ 
/*     */     
/*  54 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/*  55 */     CaracteristicasDAO cf = new CaracteristicasDAO();
/*  56 */     Collection arr = cf.cargarTodosParaServicio(solicitud.getCodigoServicio(), "T", "T");
/*  57 */     cf.close();
/*     */ 
/*     */     
/*  60 */     boolean fondo = true;
/*     */     
/*  62 */     Iterator iterator = arr.iterator();
/*  63 */     while (iterator.hasNext()) {
/*  64 */       CaracteristicasDTO car = (CaracteristicasDTO)iterator.next();
/*  65 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  67 */       fondo = !fondo;
/*  68 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  70 */       DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*  71 */       dsf.cargarParaSolicitud(solicitud.getNumero(), car.getCodigo());
/*  72 */       DetalleSolicitudDTO ds = dsf.next();
/*  73 */       String sDescripcion = "";
/*  74 */       while (ds != null) {
/*  75 */         sDescripcion = sDescripcion + ds.getObservacion() + " ";
/*  76 */         ds = dsf.next();
/*     */       } 
/*  78 */       dsf.close();
/*     */       
/*  80 */       eltr.appendChild(newtd("" + car.getDescripcion(), false));
/*  81 */       eltr.appendChild(newtd("" + sDescripcion, false));
/*     */       
/*  83 */       tabla.appendChild(eltr);
/*     */     } 
/*  85 */     arr.clear();
/*     */     
/*  87 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  88 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  92 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  93 */     atrib.setValue(valor);
/*  94 */     return atrib;
/*     */   }
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/*  98 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  99 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 100 */     if (alinear) {
/* 101 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 103 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 104 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerDetalleSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */