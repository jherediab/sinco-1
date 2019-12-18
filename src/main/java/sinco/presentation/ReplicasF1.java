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
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.ReplicasF1;
/*     */ import sinco.presentation.ReplicasF1HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class ReplicasF1
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ReplicasF1HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  32 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */     
/*  37 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  38 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/*  39 */     sf.close();
/*     */     
/*  41 */     if (regSol == null) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudAnulada"));
/*     */     }
/*  44 */     this.pagHTML = (ReplicasF1HTML)comms.xmlcFactory.create(ReplicasF1HTML.class);
/*  45 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumero());
/*  46 */     this.pagHTML.setTextServicio(regSol.getNombreServicio());
/*  47 */     this.pagHTML.setTextAreap(regSol.getNombreAreaProveedora());
/*  48 */     this.pagHTML.setTextAreacliente(regSol.getNombreAreaCliente());
/*  49 */     this.pagHTML.setTextProveedor(regSol.getNombreProveedor());
/*  50 */     this.pagHTML.setTextCliente(regSol.getNombreCliente());
/*     */ 
/*     */     
/*  53 */     this.pagHTML.setTextFechavigencia(regSol.getFechaVigencia());
/*  54 */     this.pagHTML.setTextFechaestimada(regSol.getFechaEstimadaTerminacion());
/*  55 */     this.pagHTML.setTextFechaEscalamientos(regSol.getFechaBaseEscalamientos());
/*  56 */     this.pagHTML.setTextEstado(regSol.getNombreEstado());
/*  57 */     this.pagHTML.setTextUnidad("" + regSol.getDuracion() + " " + regSol.getUnidadMedida());
/*     */     
/*  59 */     this.pagHTML.getElementIdSolicitud().setValue("" + idsol);
/*     */     
/*  61 */     SisUsuariosDAO rsPersonas = new SisUsuariosDAO();
/*  62 */     Collection arr = rsPersonas.cargarActivoDeArea(regSol.getAreaProveedor());
/*     */     
/*  64 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  65 */     boolean fondo = true;
/*  66 */     boolean hay = false;
/*     */     
/*  68 */     Iterator iterator = arr.iterator();
/*  69 */     while (iterator.hasNext()) {
/*  70 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/*  71 */       hay = true;
/*     */       
/*  73 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  75 */       fondo = !fondo;
/*  76 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  78 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/*  80 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/*  81 */       checkbox.setAttribute("type", "checkbox");
/*  82 */       checkbox.setName("_" + regPersona.getCodigoEmpleado());
/*  83 */       checkbox.setValue("" + regPersona.getCodigoEmpleado());
/*  84 */       tdMarca.appendChild(checkbox);
/*     */       
/*  86 */       eltr.appendChild(tdMarca);
/*  87 */       eltr.appendChild(newtd("" + regPersona.getNombre()));
/*     */       
/*  89 */       hte.appendChild(eltr);
/*     */     } 
/*  91 */     if (!hay) {
/*  92 */       Element formacaracteristicas = this.pagHTML.getElementDivPersonas();
/*  93 */       formacaracteristicas.getParentNode().removeChild(formacaracteristicas);
/*     */     } 
/*  95 */     arr.clear();
/*     */     
/*  97 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  98 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 102 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 103 */     atrib.setValue(valor);
/* 104 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 117 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 118 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 119 */     td.setAttributeNode(newAttr("class", "letraMin"));
/* 120 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 121 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ReplicasF1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */