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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesCerradasHTML;
/*     */ import sinco.presentation.SolicitudesResueltas;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesResueltas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesCerradasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  32 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  33 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/*  35 */     if (fechaInicio != null && !fechaInicio.equals("") && 
/*  36 */       !Utilidades.validarFormatoFecha(fechaInicio)) {
/*  37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fechaInicio"));
/*     */     }
/*     */ 
/*     */     
/*  41 */     if (fechaFin != null && !fechaFin.equals("") && 
/*  42 */       !Utilidades.validarFormatoFecha(fechaFin)) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fechaFin"));
/*     */     }
/*     */ 
/*     */     
/*  47 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/*  48 */     miFecha.fechaMasDias(1L);
/*  49 */     fechaFin = miFecha.getFecha();
/*     */ 
/*     */     
/*  52 */     this.pagHTML = (SolicitudesCerradasHTML)comms.xmlcFactory.create(SolicitudesCerradasHTML.class);
/*     */     
/*  54 */     this.pagHTML.setTextMensaje1("Area Proveedor");
/*  55 */     this.pagHTML.setTextMensaje2("Proveedor");
/*     */     
/*  57 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/*  58 */     int id = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  60 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  61 */     rsSol.cargarTodosCerradasPedidasPor(id, fechaInicio, fechaFin);
/*  62 */     VSolicitudesDTO regSol = rsSol.next();
/*     */     
/*  64 */     boolean fondo = true;
/*  65 */     int cuantas = 0;
/*  66 */     while (regSol != null) {
/*  67 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  69 */       fondo = !fondo;
/*  70 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  72 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  73 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero();
/*  74 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  75 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*  76 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  77 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*     */       
/*  79 */       hte.appendChild(eltr);
/*  80 */       regSol = rsSol.next();
/*  81 */       cuantas++;
/*     */     } 
/*  83 */     rsSol.close();
/*  84 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  85 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  86 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  95 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  96 */     atrib.setValue(valor);
/*  97 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 107 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 108 */     Element enlace = this.pagHTML.createElement("a");
/* 109 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 110 */     enlace.appendChild(hijo);
/* 111 */     Attr donde = this.pagHTML.createAttribute("href");
/* 112 */     donde.setValue(vinculo);
/* 113 */     enlace.setAttributeNode(donde);
/* 114 */     td.appendChild(enlace);
/* 115 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 116 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 126 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 127 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 128 */     if (alinear) {
/* 129 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 131 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 132 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesResueltas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */