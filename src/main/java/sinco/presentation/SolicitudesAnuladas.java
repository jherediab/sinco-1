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
/*     */ import sinco.presentation.SolicitudesAnuladas;
/*     */ import sinco.presentation.SolicitudesAnuladasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesAnuladas
/*     */   implements HttpPresentation {
/*     */   private static SolicitudesAnuladasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
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
/*  51 */     pagHTML = (SolicitudesAnuladasHTML)comms.xmlcFactory.create(SolicitudesAnuladasHTML.class);
/*     */     
/*  53 */     HTMLTableElement hte = pagHTML.getElementSolicitudes();
/*  54 */     int id = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  56 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  57 */     rsSol.cargarAnuladasPedidasA(id, fechaInicio, fechaFin);
/*  58 */     VSolicitudesDTO regSol = rsSol.next();
/*     */     
/*  60 */     boolean fondo = true;
/*  61 */     while (regSol != null) {
/*  62 */       HTMLElement eltr = (HTMLElement)pagHTML.createElement("tr");
/*  63 */       fondo = !fondo;
/*  64 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  66 */       eltr.appendChild(newtd("" + regSol.getNumero(), false));
/*  67 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*  68 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  69 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  70 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*  71 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*  72 */       hte.appendChild(eltr);
/*  73 */       regSol = rsSol.next();
/*     */     } 
/*     */     
/*  76 */     hte = pagHTML.getElementSolicitudes2();
/*     */     
/*  78 */     rsSol.cargarAnuladasPedidasPor(id, fechaInicio, fechaFin);
/*  79 */     regSol = rsSol.next();
/*     */     
/*  81 */     fondo = true;
/*  82 */     int cuantas = 0;
/*  83 */     while (regSol != null) {
/*  84 */       HTMLElement eltr = (HTMLElement)pagHTML.createElement("tr");
/*  85 */       fondo = !fondo;
/*  86 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  88 */       eltr.appendChild(newtd("" + regSol.getNumero(), false));
/*  89 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*  90 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  91 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*  92 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  93 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*     */       
/*  95 */       hte.appendChild(eltr);
/*  96 */       regSol = rsSol.next();
/*  97 */       cuantas++;
/*     */     } 
/*  99 */     rsSol.close();
/* 100 */     pagHTML.setTextNroRegistros("" + cuantas);
/* 101 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 102 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 107 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/* 108 */     Element enlace = pagHTML.createElement("a");
/* 109 */     Node hijo = pagHTML.createTextNode(contenido);
/* 110 */     enlace.appendChild(hijo);
/* 111 */     Attr donde = pagHTML.createAttribute("href");
/* 112 */     donde.setValue(vinculo);
/* 113 */     enlace.setAttributeNode(donde);
/* 114 */     td.appendChild(enlace);
/* 115 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 116 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 119 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/* 120 */     td.appendChild(pagHTML.createTextNode(contenido));
/* 121 */     if (alinear) {
/* 122 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 124 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 125 */     return td;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 129 */     Attr atrib = pagHTML.createAttribute(tipo);
/* 130 */     atrib.setValue(valor);
/* 131 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesAnuladas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */