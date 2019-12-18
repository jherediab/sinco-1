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
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesPorAtender;
/*     */ import sinco.presentation.SolicitudesPorAtenderHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesPorAtender
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesPorAtenderHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  35 */     int escaladas = 0;
/*     */     try {
/*  37 */       escaladas = Integer.parseInt(comms.request.getParameter("escaladas"));
/*     */     }
/*  39 */     catch (Exception e) {}
/*     */     
/*  41 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  42 */     Collection<VSolicitudesDTO> arr = (escaladas == 0) ? rsSol.cargarTodosEnCursoPedidasA(idNav) : rsSol.cargarEnEscalamientoPedidasA(idNav);
/*  43 */     rsSol.close();
/*     */     
/*  45 */     boolean fondo = true;
/*  46 */     int cuantas = 0;
/*     */     
/*  48 */     this.pagHTML = (SolicitudesPorAtenderHTML)comms.xmlcFactory.create(SolicitudesPorAtenderHTML.class);
/*     */     
/*  50 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  51 */     Iterator<VSolicitudesDTO> iterator = arr.iterator();
/*  52 */     while (iterator.hasNext()) {
/*  53 */       VSolicitudesDTO regSol = (VSolicitudesDTO)iterator.next();
/*  54 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  55 */       fondo = !fondo;
/*  56 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  58 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), true));
/*  59 */       String sPagina = "VSPorAt.po?solicitud=" + regSol.getNumero();
/*     */       
/*  61 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  62 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  63 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*  64 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*  65 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*  66 */       eltr.appendChild(newtd("" + regSol.getNumeroHijasAbiertas() + " de " + regSol.getNumeroHijas(), false));
/*     */       
/*  68 */       hte.appendChild(eltr);
/*  69 */       cuantas++;
/*     */     } 
/*  71 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  72 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  73 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  82 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  83 */     Element enlace = this.pagHTML.createElement("a");
/*  84 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  85 */     enlace.appendChild(hijo);
/*  86 */     Attr donde = this.pagHTML.createAttribute("href");
/*  87 */     donde.setValue(vinculo);
/*  88 */     enlace.setAttributeNode(donde);
/*  89 */     td.appendChild(enlace);
/*  90 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  91 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 101 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 102 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 103 */     if (alinear) {
/* 104 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 106 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 107 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 117 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 118 */     atrib.setValue(valor);
/* 119 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesPorAtender.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */