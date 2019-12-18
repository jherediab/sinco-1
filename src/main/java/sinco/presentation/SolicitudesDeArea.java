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
/*     */ import sinco.presentation.SolicitudesDeArea;
/*     */ import sinco.presentation.SolicitudesDeAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesDeArea
/*     */   implements HttpPresentation {
/*     */   private static SolicitudesDeAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  33 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*  34 */     int tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*  35 */     int plan = 0;
/*     */     try {
/*  37 */       plan = Integer.parseInt(comms.request.getParameter("plan"));
/*     */     }
/*  39 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  42 */     int rol = 0;
/*     */     try {
/*  44 */       rol = Integer.parseInt(comms.request.getParameter("rol"));
/*     */     }
/*  46 */     catch (Exception e) {}
/*     */     
/*  48 */     int servicio = 0;
/*     */     try {
/*  50 */       servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  52 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  55 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  56 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */ 
/*     */     
/*  59 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/*  61 */     Collection arrDatos = rs.solicitudesDeArea(tipo, area, servicio, fechaInicio, fechaFin, rol);
/*  62 */     rs.close();
/*     */ 
/*     */     
/*  65 */     pagHTML = (SolicitudesDeAreaHTML)comms.xmlcFactory.create(SolicitudesDeAreaHTML.class);
/*  66 */     switch (tipo) {
/*     */       case 1:
/*  68 */         pagHTML.setTextTitulo("SOLICITUDES POR ATENDER");
/*     */         break;
/*     */       case 2:
/*  71 */         pagHTML.setTextTitulo("SOLICITUDES EN ESCALAMIENTO");
/*     */         break;
/*     */       case 3:
/*  74 */         pagHTML.setTextTitulo("PLANES DIGALO");
/*     */         break;
/*     */       case 4:
/*  77 */         pagHTML.setTextTitulo("REPORTE ESPECIFICO");
/*     */         break;
/*     */     } 
/*     */     
/*  81 */     HTMLTableElement tablaElem = pagHTML.getElementSolicitudes();
/*  82 */     boolean fondo = true;
/*  83 */     int cuantas = 0;
/*  84 */     Iterator iter = arrDatos.iterator();
/*  85 */     while (iter.hasNext()) {
/*  86 */       VSolicitudesDTO reg = (VSolicitudesDTO)iter.next();
/*     */       
/*  88 */       HTMLElement eltr = (HTMLElement)pagHTML.createElement("tr");
/*  89 */       fondo = !fondo;
/*  90 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  92 */       eltr.appendChild(newtd("" + reg.getNombreAreaProveedora(), false));
/*  93 */       eltr.appendChild(newtd("" + reg.getNombreProveedor(), false));
/*  94 */       String sPagina = "VSEnCurso.po?solicitud=" + reg.getNumero() + "&lectura=1";
/*  95 */       eltr.appendChild(newtdhref("" + reg.getNumero(), sPagina));
/*  96 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVigencia()), false));
/*  97 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaEstimadaTerminacion()), false));
/*  98 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), "VerDetalleSolicitud.po?solicitud=" + reg.getNumero()));
/*  99 */       eltr.appendChild(newtd("" + reg.getNombreAreaCliente(), false));
/* 100 */       eltr.appendChild(newtd("" + reg.getNombreCliente(), false));
/* 101 */       eltr.appendChild(newtd("" + reg.getNivelEscalamiento(), true));
/* 102 */       tablaElem.appendChild(eltr);
/* 103 */       cuantas++;
/*     */     } 
/* 105 */     arrDatos.clear();
/* 106 */     pagHTML.setTextNroRegistros("" + cuantas);
/* 107 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 108 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 119 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/* 120 */     Element enlace = pagHTML.createElement("a");
/* 121 */     Node hijo = pagHTML.createTextNode(contenido);
/* 122 */     enlace.appendChild(hijo);
/* 123 */     Attr donde = pagHTML.createAttribute("href");
/* 124 */     donde.setValue(vinculo);
/* 125 */     enlace.setAttributeNode(donde);
/* 126 */     td.appendChild(enlace);
/* 127 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 128 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 137 */     HTMLElement td = (HTMLElement)pagHTML.createElement("td");
/* 138 */     td.appendChild(pagHTML.createTextNode(contenido));
/* 139 */     if (alinear) {
/* 140 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 142 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 143 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 153 */     Attr atrib = pagHTML.createAttribute(tipo);
/* 154 */     atrib.setValue(valor);
/* 155 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesDeArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */