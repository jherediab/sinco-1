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
/*     */ import sinco.presentation.SolicitudesEnCurso;
/*     */ import sinco.presentation.SolicitudesEnCursoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesEnCurso
/*     */   implements HttpPresentation {
/*     */   private SolicitudesEnCursoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  29 */     this.pagHTML = (SolicitudesEnCursoHTML)comms.xmlcFactory.create(SolicitudesEnCursoHTML.class);
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  32 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  33 */     rsSol.cargarTodosEnCursoPedidasPor(idNav);
/*  34 */     VSolicitudesDTO regSol = rsSol.next();
/*     */     
/*  36 */     HTMLTableSectionElement hte = this.pagHTML.getElementSolicitudes();
/*     */     
/*  38 */     boolean fondo = true;
/*  39 */     int cuantas = 0;
/*  40 */     while (regSol != null) {
/*  41 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  42 */       fondo = !fondo;
/*  43 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  45 */       eltr.appendChild(newTD("" + regSol.getNumeroMostrar(), true));
/*  46 */       String sPagina = "VSEnCurso.po?solicitud=" + (regSol.getMacroServicio().equals("S") ? regSol.getNumeroMostrar() : regSol.getNumero());
/*  47 */       eltr.appendChild(newtdhref(regSol.getNombreServicio(), sPagina));
/*  48 */       eltr.appendChild(newTD("" + regSol.getNombreAreaProveedora(), false));
/*  49 */       eltr.appendChild(newTD("" + regSol.getNombreProveedor(), false));
/*  50 */       eltr.appendChild(newTD("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*     */       
/*  52 */       hte.appendChild(eltr);
/*  53 */       regSol = rsSol.next();
/*  54 */       cuantas++;
/*     */     } 
/*  56 */     rsSol.close();
/*  57 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  58 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  59 */     comms.response.writeDOM(this.pagHTML);
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
/*  70 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  71 */     atrib.setValue(valor);
/*  72 */     return atrib;
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
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  87 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  88 */     Element enlace = this.pagHTML.createElement("a");
/*  89 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  90 */     enlace.appendChild(hijo);
/*  91 */     Attr donde = this.pagHTML.createAttribute("href");
/*  92 */     donde.setValue(vinculo);
/*  93 */     enlace.setAttributeNode(donde);
/*  94 */     td.appendChild(enlace);
/*  95 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  96 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newTD(String contenido, boolean align) {
/* 100 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 101 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 102 */     if (align) {
/* 103 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 105 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 106 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesEnCurso.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */