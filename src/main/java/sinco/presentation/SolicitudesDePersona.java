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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesDeAreaHTML;
/*     */ import sinco.presentation.SolicitudesDePersona;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesDePersona
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesDeAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  34 */     int persona = Integer.parseInt(comms.request.getParameter("persona"));
/*  35 */     int tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*  36 */     int plan = 0;
/*     */     try {
/*  38 */       plan = Integer.parseInt(comms.request.getParameter("plan"));
/*     */     }
/*  40 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  43 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  44 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */ 
/*     */     
/*  47 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*  48 */     Collection arr = rs.solicitudesDePersona(tipo, persona, plan, fechaInicio, fechaFin);
/*  49 */     rs.close();
/*     */     
/*  51 */     this.pagHTML = (SolicitudesDeAreaHTML)comms.xmlcFactory.create(SolicitudesDeAreaHTML.class);
/*  52 */     switch (tipo) {
/*     */       case 1:
/*  54 */         this.pagHTML.setTextTitulo("SOLICITUDES POR ATENDER");
/*     */         break;
/*     */       case 2:
/*  57 */         this.pagHTML.setTextTitulo("SOLICITUDES EN ESCALAMIENTO");
/*     */         break;
/*     */       case 3:
/*  60 */         this.pagHTML.setTextTitulo("PLANES DIGALO");
/*     */         break;
/*     */     } 
/*     */     
/*  64 */     HTMLTableElement tablaElem = this.pagHTML.getElementSolicitudes();
/*  65 */     boolean fondo = true;
/*  66 */     int cuantas = 0;
/*  67 */     Iterator iter = arr.iterator();
/*  68 */     while (iter.hasNext()) {
/*  69 */       VSolicitudesDTO reg = (VSolicitudesDTO)iter.next();
/*     */       
/*  71 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  73 */       fondo = !fondo;
/*  74 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  76 */       eltr.appendChild(newtd("" + reg.getNombreAreaProveedora(), false));
/*  77 */       eltr.appendChild(newtd("" + reg.getNombreProveedor(), false));
/*  78 */       eltr.appendChild(newtd("" + reg.getNombreCliente(), false));
/*     */       
/*  80 */       String sPagina = "VSEnCurso.po?solicitud=" + reg.getNumero() + "&lectura=1";
/*  81 */       eltr.appendChild(newtdhref("" + reg.getNumero(), sPagina));
/*  82 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVigencia()), false));
/*  83 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaEstimadaTerminacion()), false));
/*     */ 
/*     */       
/*  86 */       sPagina = "VerDetalleSolicitud.po?solicitud=" + reg.getNumero();
/*  87 */       eltr.appendChild(newtdhref(reg.getNombreServicio(), sPagina));
/*  88 */       eltr.appendChild(newtd("" + reg.getNombreAreaCliente(), false));
/*  89 */       eltr.appendChild(newtd("" + reg.getNombreCliente(), false));
/*  90 */       eltr.appendChild(newtd("" + reg.getNivelEscalamiento(), false));
/*  91 */       tablaElem.appendChild(eltr);
/*  92 */       cuantas++;
/*     */     } 
/*  94 */     arr.clear();
/*  95 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  96 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  97 */     comms.response.writeDOM(this.pagHTML);
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
/* 108 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 109 */     atrib.setValue(valor);
/* 110 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 120 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 121 */     Element enlace = this.pagHTML.createElement("a");
/* 122 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 123 */     enlace.appendChild(hijo);
/* 124 */     Attr donde = this.pagHTML.createAttribute("href");
/* 125 */     donde.setValue(vinculo);
/* 126 */     enlace.setAttributeNode(donde);
/* 127 */     td.appendChild(enlace);
/* 128 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 129 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 139 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 140 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 141 */     if (alinear) {
/* 142 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 144 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 145 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesDePersona.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */