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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.presentation.AMCapturaAreas;
/*     */ import sinco.presentation.AMCapturaAreasHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMCapturaAreas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMCapturaAreasHTML pagHTML;
/*  27 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  30 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  31 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  34 */     this.elUsuario = "" + comms.session.getUser().getName();
/*  35 */     this.pagHTML = (AMCapturaAreasHTML)comms.xmlcFactory.create(AMCapturaAreasHTML.class);
/*  36 */     listar(comms);
/*  37 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/*  50 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  51 */     atrib.setValue(valor);
/*  52 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/*  63 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  64 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  65 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  66 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  74 */     String descripcion = comms.request.getParameter("descripcion");
/*  75 */     if (descripcion == null) descripcion = "";
/*     */     
/*  77 */     if (descripcion.length() == 0) {
/*  78 */       return 0;
/*     */     }
/*     */     
/*  81 */     AreasDAO rs = new AreasDAO();
/*  82 */     if (!rs.getEstadoConexion()) {
/*  83 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  86 */     int cuantos = 0;
/*  87 */     Collection<AreasDTO> arr = rs.cargarParecidas(descripcion);
/*  88 */     rs.close();
/*     */     
/*  90 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  91 */     boolean fondo = true;
/*  92 */     Iterator<AreasDTO> iterator = arr.iterator();
/*  93 */     while (iterator.hasNext()) {
/*  94 */       AreasDTO reg = (AreasDTO)iterator.next();
/*  95 */       cuantos++;
/*  96 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  97 */       fondo = !fondo;
/*  98 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 100 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 101 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 102 */       checkbox.setAttribute("type", "checkbox");
/* 103 */       checkbox.setName("A_" + Utilidades.formato(reg.getCodigo(), 6) + reg.getDescripcion());
/* 104 */       tdMarca.appendChild(checkbox);
/* 105 */       eltr.appendChild(tdMarca);
/*     */       
/* 107 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 108 */       hte.appendChild(eltr);
/* 109 */       hte.appendChild(eltr);
/*     */     } 
/* 111 */     return cuantos;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMCapturaAreas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */