/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesNoEnviadas;
/*     */ import sinco.presentation.SolicitudesNoEnviadasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesNoEnviadas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesNoEnviadasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  29 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  31 */     this.pagHTML = (SolicitudesNoEnviadasHTML)comms.xmlcFactory.create(SolicitudesNoEnviadasHTML.class);
/*     */     
/*  33 */     int idsol = 0;
/*     */     try {
/*  35 */       idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */     }
/*  37 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  41 */     int id = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  43 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  44 */     rsSol.cargarTodosNoEnviadasPedidasPor(id, idsol);
/*  45 */     VSolicitudesDTO regSol = rsSol.next();
/*     */     
/*  47 */     boolean fondo = true;
/*  48 */     int cuantas = 0;
/*  49 */     HTMLTableSectionElement hte = this.pagHTML.getElementSolicitudes();
/*  50 */     while (regSol != null) {
/*  51 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  53 */       fondo = !fondo;
/*  54 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  56 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  57 */       String sPagina = "VerSolicitudNoEnviada.po?solicitud=" + regSol.getNumero();
/*     */       
/*  59 */       if (regSol.getAreaProveedor() == 0) {
/*  60 */         sPagina = "SolicitudPendProveedor.po?_operacion=P&solicitud=" + regSol.getNumero() + "&solPadre=" + regSol.getNumero();
/*     */       }
/*     */       
/*  63 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  64 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*  65 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  66 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaGenerada()), false));
/*     */       
/*  68 */       hte.appendChild(eltr);
/*  69 */       regSol = rsSol.next();
/*  70 */       cuantas++;
/*     */     } 
/*  72 */     rsSol.close();
/*  73 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  74 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  75 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  86 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  87 */     atrib.setValue(valor);
/*  88 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  98 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  99 */     Element enlace = this.pagHTML.createElement("a");
/* 100 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 101 */     enlace.appendChild(hijo);
/* 102 */     Attr donde = this.pagHTML.createAttribute("href");
/* 103 */     donde.setValue(vinculo);
/* 104 */     enlace.setAttributeNode(donde);
/* 105 */     td.appendChild(enlace);
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
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 117 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 118 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 119 */     if (alinear) {
/* 120 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 122 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 123 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesNoEnviadas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */