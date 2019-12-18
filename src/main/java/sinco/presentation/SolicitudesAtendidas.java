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
/*     */ import sinco.presentation.SolicitudesAtendidas;
/*     */ import sinco.presentation.SolicitudesCerradasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesAtendidas implements HttpPresentation {
/*     */   private static SolicitudesCerradasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  29 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  31 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  32 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/*  34 */     if (fechaInicio != null && !fechaInicio.equals("") && 
/*  35 */       !Utilidades.validarFormatoFecha(fechaInicio)) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fechaInicio"));
/*     */     }
/*     */ 
/*     */     
/*  40 */     if (fechaFin != null && !fechaFin.equals("") && 
/*  41 */       !Utilidades.validarFormatoFecha(fechaFin)) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fechaFin"));
/*     */     }
/*     */ 
/*     */     
/*  46 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/*  47 */     miFecha.fechaMasDias(1L);
/*  48 */     fechaFin = miFecha.getFecha();
/*     */     
/*  50 */     pagHTML = (SolicitudesCerradasHTML)comms.xmlcFactory.create(SolicitudesCerradasHTML.class);
/*     */     
/*  52 */     pagHTML.setTextMensaje1("Area Cliente");
/*  53 */     pagHTML.setTextMensaje2("Cliente");
/*     */     
/*  55 */     HTMLTableElement hte = pagHTML.getElementSolicitudes();
/*  56 */     int id = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  58 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  59 */     rsSol.cargarTodosCerradasPedidasA(id, fechaInicio, fechaFin);
/*  60 */     VSolicitudesDTO regSol = rsSol.next();
/*     */     
/*  62 */     boolean fondo = true;
/*  63 */     int cuantas = 0;
/*  64 */     while (regSol != null) {
/*  65 */       HTMLElement eltr = (HTMLElement)pagHTML.createElement("tr");
/*  66 */       fondo = !fondo;
/*  67 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  69 */       eltr.appendChild(newtd("" + regSol.getNumero(), false));
/*  70 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*  71 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  72 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  73 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*  74 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaRealTerminacion()), false));
/*  75 */       hte.appendChild(eltr);
/*  76 */       regSol = rsSol.next();
/*  77 */       cuantas++;
/*     */     } 
/*  79 */     rsSol.close();
/*  80 */     pagHTML.setTextNroRegistros("" + cuantas);
/*  81 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  82 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  86 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/*  87 */     Element enlace = pagHTML.createElement("a");
/*  88 */     Node hijo = pagHTML.createTextNode(contenido);
/*  89 */     enlace.appendChild(hijo);
/*  90 */     Attr donde = pagHTML.createAttribute("href");
/*  91 */     donde.setValue(vinculo);
/*  92 */     enlace.setAttributeNode(donde);
/*  93 */     td.appendChild(enlace);
/*  94 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  95 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/*  98 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/*  99 */     td.appendChild(pagHTML.createTextNode(contenido));
/* 100 */     if (alinear) {
/* 101 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 103 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 104 */     return td;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 108 */     Attr atrib = pagHTML.createAttribute(tipo);
/* 109 */     atrib.setValue(valor);
/* 110 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesAtendidas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */