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
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesEnEstado;
/*     */ import sinco.presentation.SolicitudesEnEstadoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesEnEstado
/*     */   implements HttpPresentation {
/*     */   private SolicitudesEnEstadoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  29 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  31 */     int tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*  32 */     int proveedor = Integer.parseInt(comms.request.getParameter("proveedor"));
/*  33 */     int operacion = Integer.parseInt(comms.request.getParameter("operacion"));
/*     */     
/*  35 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  36 */     String fechaFin = comms.request.getParameter("fechaFin");
/*  37 */     int estado = 0;
/*     */     try {
/*  39 */       estado = Integer.parseInt(comms.request.getParameter("estado"));
/*     */     }
/*  41 */     catch (Exception e) {}
/*     */     
/*  43 */     this.pagHTML = (SolicitudesEnEstadoHTML)comms.xmlcFactory.create(SolicitudesEnEstadoHTML.class);
/*  44 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/*     */     
/*  46 */     boolean rta = false;
/*  47 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  48 */     switch (operacion) {
/*     */       case 7:
/*  50 */         this.pagHTML.setTextTitulo("Solicitudes de ...");
/*  51 */         this.pagHTML.setTextTitulo1("Area Cliente");
/*  52 */         this.pagHTML.setTextTitulo2("Cliente");
/*  53 */         rta = sf.cargarSolicitudesDeEstado(tipo, proveedor, fechaInicio, fechaFin, estado);
/*     */         break;
/*     */     } 
/*  56 */     if (!rta) {
/*  57 */       sf.close();
/*  58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEnConsulta"));
/*     */     } 
/*  60 */     VSolicitudesDTO regSol = sf.next();
/*  61 */     int cuantas = 0;
/*  62 */     boolean fondo = true;
/*  63 */     while (regSol != null) {
/*  64 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  66 */       fondo = !fondo;
/*  67 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */ 
/*     */       
/*  70 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  71 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
/*  72 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*     */       
/*  74 */       if (operacion == 7) {
/*  75 */         eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  76 */         eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*     */       } else {
/*     */         
/*  79 */         eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  80 */         eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*     */       } 
/*     */       
/*  83 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaGenerada()), false));
/*     */       
/*  85 */       eltr.appendChild(newtd("" + regSol.getNombreEstado(), false));
/*     */       
/*  87 */       hte.appendChild(eltr);
/*  88 */       regSol = sf.next();
/*  89 */       cuantas++;
/*     */     } 
/*  91 */     sf.close();
/*  92 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  93 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  94 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 104 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 105 */     atrib.setValue(valor);
/* 106 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 116 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 117 */     Element enlace = this.pagHTML.createElement("a");
/* 118 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 119 */     enlace.appendChild(hijo);
/* 120 */     Attr donde = this.pagHTML.createAttribute("href");
/* 121 */     donde.setValue(vinculo);
/* 122 */     enlace.setAttributeNode(donde);
/* 123 */     td.appendChild(enlace);
/* 124 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 125 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 128 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 129 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 130 */     if (alinear) {
/* 131 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 133 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 134 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesEnEstado.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */