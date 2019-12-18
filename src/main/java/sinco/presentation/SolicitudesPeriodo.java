/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
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
/*     */ import sinco.presentation.SolicitudesEnEstadoHTML;
/*     */ import sinco.presentation.SolicitudesPeriodo;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesPeriodo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesEnEstadoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  35 */     int tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*  36 */     int proveedor = Integer.parseInt(comms.request.getParameter("proveedor"));
/*  37 */     int operacion = Integer.parseInt(comms.request.getParameter("operacion"));
/*     */     
/*  39 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  40 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  41 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     
/*  43 */     this.pagHTML = (SolicitudesEnEstadoHTML)comms.xmlcFactory.create(SolicitudesEnEstadoHTML.class);
/*  44 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/*     */     
/*  46 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  47 */     Collection arr = new ArrayList();
/*  48 */     switch (operacion) {
/*     */       case 1:
/*  50 */         this.pagHTML.setTextTitulo("Solicitudes con Oportunidad Excede");
/*  51 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 1, "E", 1, anno, mes1, mes2);
/*     */         break;
/*     */       case 2:
/*  54 */         this.pagHTML.setTextTitulo("Solicitudes con Oportunidad Cumple");
/*  55 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 1, "B", 1, anno, mes1, mes2);
/*     */         break;
/*     */       case 3:
/*  58 */         this.pagHTML.setTextTitulo("Solicitudes con Oportunidad No Cumple");
/*  59 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 1, "R", 1, anno, mes1, mes2);
/*     */         break;
/*     */       case 4:
/*  62 */         this.pagHTML.setTextTitulo("Solicitudes con Confiabilidad Excede");
/*  63 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 2, "E", 1, anno, mes1, mes2);
/*     */         break;
/*     */       case 5:
/*  66 */         this.pagHTML.setTextTitulo("Solicitudes con Confiabilidad Cumple");
/*  67 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 2, "B", 1, anno, mes1, mes2);
/*     */         break;
/*     */       case 6:
/*  70 */         this.pagHTML.setTextTitulo("Solicitudes con Confiabilidad No Cumple");
/*  71 */         arr = sf.cargarSolicitudesCalificadas(tipo, proveedor, 2, "R", 1, anno, mes1, mes2);
/*     */         break;
/*     */     } 
/*  74 */     sf.close();
/*  75 */     int cuantas = 0;
/*  76 */     boolean fondo = true;
/*     */     
/*  78 */     Iterator iter = arr.iterator();
/*  79 */     while (iter.hasNext()) {
/*  80 */       VSolicitudesDTO regSol = (VSolicitudesDTO)iter.next();
/*     */       
/*  82 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  84 */       fondo = !fondo;
/*  85 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */ 
/*     */       
/*  88 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  89 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*  90 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*     */       
/*  92 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  93 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*     */       
/*  95 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaVigencia()), false));
/*     */       
/*  97 */       eltr.appendChild(newtd("" + regSol.getNombreEstado(), false));
/*     */       
/*  99 */       hte.appendChild(eltr);
/* 100 */       cuantas++;
/*     */     } 
/* 102 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 103 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 104 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 114 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 115 */     atrib.setValue(valor);
/* 116 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 126 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 127 */     Element enlace = this.pagHTML.createElement("a");
/* 128 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 129 */     enlace.appendChild(hijo);
/* 130 */     Attr donde = this.pagHTML.createAttribute("href");
/* 131 */     donde.setValue(vinculo);
/* 132 */     enlace.setAttributeNode(donde);
/* 133 */     td.appendChild(enlace);
/* 134 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 135 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 138 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 139 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 140 */     if (alinear) {
/* 141 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 143 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 144 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesPeriodo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */