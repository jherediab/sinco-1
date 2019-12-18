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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesPorAtenderPorArea;
/*     */ import sinco.presentation.SolicitudesPorAtenderPorAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesPorAtenderPorArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesPorAtenderPorAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  37 */     int area = 0;
/*     */     try {
/*  39 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  41 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  44 */     int proveedor = 0;
/*     */     try {
/*  46 */       proveedor = Integer.parseInt(comms.request.getParameter("proveedor"));
/*     */     }
/*  48 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  51 */     this.pagHTML = (SolicitudesPorAtenderPorAreaHTML)comms.xmlcFactory.create(SolicitudesPorAtenderPorAreaHTML.class);
/*     */     
/*  53 */     if (area != 0) {
/*  54 */       AreasDAO af = new AreasDAO();
/*  55 */       AreasDTO regArea = af.getArea(area);
/*  56 */       af.close();
/*  57 */       this.pagHTML.setTextNombreArea(regArea.getDescripcion());
/*     */     
/*     */     }
/*  60 */     else if (proveedor != 0) {
/*  61 */       SisUsuariosDAO pf = new SisUsuariosDAO();
/*  62 */       SisUsuariosDTO p = pf.cargarRegistro(proveedor);
/*  63 */       this.pagHTML.setTextNombreArea(p.getNombre());
/*     */     } 
/*     */     
/*  66 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  67 */     Collection<VSolicitudesDTO> arr = (proveedor != 0) ? rsSol.cargarTodosEnCursoPedidasA(proveedor) : rsSol.cargarTodosEnCursoPedidasAArea(area);
/*  68 */     rsSol.close();
/*     */     
/*  70 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/*  72 */     boolean fondo = true;
/*  73 */     int cuantas = 0;
/*  74 */     Iterator<VSolicitudesDTO> iterator = arr.iterator();
/*  75 */     while (iterator.hasNext()) {
/*  76 */       VSolicitudesDTO regSol = (VSolicitudesDTO)iterator.next();
/*     */       
/*  78 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  79 */       fondo = !fondo;
/*  80 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  82 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  83 */       String sPagina = "VSPorAt.po?solicitud=" + regSol.getNumero();
/*     */       
/*  85 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  86 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  87 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*  88 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  89 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*  90 */       eltr.appendChild(newtd("" + regSol.getNumeroHijasAbiertas() + " de " + regSol.getNumeroHijas(), false));
/*     */       
/*  92 */       hte.appendChild(eltr);
/*  93 */       cuantas++;
/*     */     } 
/*  95 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  96 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  97 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 102 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 103 */     Element enlace = this.pagHTML.createElement("a");
/* 104 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 105 */     enlace.appendChild(hijo);
/* 106 */     Attr donde = this.pagHTML.createAttribute("href");
/* 107 */     donde.setValue(vinculo);
/* 108 */     enlace.setAttributeNode(donde);
/* 109 */     td.appendChild(enlace);
/* 110 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 111 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 114 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 115 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 116 */     if (alinear) {
/* 117 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 119 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 120 */     return td;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 124 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 125 */     atrib.setValue(valor);
/* 126 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesPorAtenderPorArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */