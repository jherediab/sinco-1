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
/*     */ import sinco.presentation.Reporte01F4;
/*     */ import sinco.presentation.Reporte01F4HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Reporte01F4
/*     */   implements HttpPresentation
/*     */ {
/*     */   private Reporte01F4HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  38 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  40 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  41 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/*  43 */     int rol = 0;
/*     */     try {
/*  45 */       rol = Integer.parseInt(comms.request.getParameter("rol"));
/*     */     }
/*  47 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  50 */     int area = 0;
/*     */     try {
/*  52 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  54 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  57 */     int estado = 0;
/*     */     try {
/*  59 */       estado = Integer.parseInt(comms.request.getParameter("estado"));
/*     */     }
/*  61 */     catch (Exception e) {}
/*     */     
/*  63 */     int servicio = 0;
/*     */     try {
/*  65 */       servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  67 */     catch (Exception e) {}
/*     */     
/*  69 */     int area2 = 0;
/*     */     try {
/*  71 */       area2 = Integer.parseInt(comms.request.getParameter("area2"));
/*     */     }
/*  73 */     catch (Exception e) {}
/*     */     
/*  75 */     int persona = 0;
/*     */     try {
/*  77 */       persona = Integer.parseInt(comms.request.getParameter("persona"));
/*     */     }
/*  79 */     catch (Exception e) {}
/*     */     
/*  81 */     int agrupar = 0;
/*     */     try {
/*  83 */       agrupar = Integer.parseInt(comms.request.getParameter("agrupar"));
/*     */     }
/*  85 */     catch (Exception e) {}
/*     */     
/*  87 */     this.pagHTML = (Reporte01F4HTML)comms.xmlcFactory.create(Reporte01F4HTML.class);
/*     */     
/*  89 */     if (agrupar == 1) {
/*  90 */       resultadosAgrupados(fechaInicio, fechaFin, rol, area, estado, servicio, area2, persona);
/*     */     } else {
/*     */       
/*  93 */       resultadosDetallados(fechaInicio, fechaFin, rol, area, estado, servicio, area2, persona);
/*     */     } 
/*  95 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  96 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   
/*     */   private long resultadosAgrupados(String fechaInicio, String fechaFin, int rol, int area, int estado, int servicio, int area2, int persona) {
/* 124 */     Collection arr = crearSentenciaGrupo(fechaInicio, fechaFin, rol, area, estado, servicio, area2, persona);
/*     */     
/* 126 */     HTMLTableElement hte = this.pagHTML.getElementDetalleG();
/* 127 */     boolean fondo = true;
/* 128 */     long cuantos = 0L;
/* 129 */     Iterator iter = arr.iterator();
/* 130 */     while (iter.hasNext()) {
/* 131 */       VSolicitudesDTO reg = (VSolicitudesDTO)iter.next();
/* 132 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 133 */       fondo = !fondo;
/* 134 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 136 */       String sPagina = "Reporte01F4.po?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin + "&rol=" + rol + "&estado=" + estado + "&servicio=" + reg.getCodigoServicio() + "&persona=" + persona;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       sPagina = sPagina + "&area=" + ((rol == 0) ? reg.getAreaProveedor() : reg.getAreaCliente());
/* 144 */       sPagina = sPagina + "&area2=" + ((rol == 1) ? reg.getAreaProveedor() : reg.getAreaCliente());
/* 145 */       eltr.appendChild(newtd("" + reg.getNombreAreaProveedora(), false, 0));
/* 146 */       eltr.appendChild(newtd("" + reg.getNombreAreaCliente(), false, 0));
/* 147 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), sPagina, 0));
/* 148 */       eltr.appendChild(newtd("" + reg.getNumero(), true, 0));
/*     */       
/* 150 */       hte.appendChild(eltr);
/* 151 */       cuantos++;
/*     */     } 
/* 153 */     arr.clear();
/*     */     
/* 155 */     Element elemento = this.pagHTML.getElementRDetalle();
/* 156 */     elemento.getParentNode().removeChild(elemento);
/* 157 */     return cuantos;
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
/*     */   private long resultadosDetallados(String fechaInicio, String fechaFin, int rol, int area, int estado, int servicio, int area2, int persona) {
/* 183 */     Collection arr = crearSentencia(fechaInicio, fechaFin, rol, area, estado, servicio, area2, persona);
/*     */     
/* 185 */     HTMLTableElement hte = this.pagHTML.getElementDetalleD();
/* 186 */     boolean fondo = true;
/* 187 */     long cuantos = 0L;
/* 188 */     Iterator iter = arr.iterator();
/* 189 */     while (iter.hasNext()) {
/* 190 */       VSolicitudesDTO reg = (VSolicitudesDTO)iter.next();
/* 191 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 192 */       fondo = !fondo;
/* 193 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 195 */       String sPagina = "VSEnCurso.po?solicitud=" + reg.getNumero() + "&lectura=1";
/* 196 */       eltr.appendChild(newtd("" + reg.getNumero(), true, 0));
/* 197 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), sPagina, 0));
/* 198 */       eltr.appendChild(newtd("" + reg.getNombreAreaProveedora(), false, 0));
/* 199 */       eltr.appendChild(newtd("" + reg.getNombreAreaCliente(), false, 0));
/* 200 */       eltr.appendChild(newtd("" + reg.getNombreProveedor(), false, 0));
/* 201 */       eltr.appendChild(newtd("" + reg.getNombreCliente(), false, 0));
/* 202 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVigencia()), false, 0));
/*     */       
/* 204 */       hte.appendChild(eltr);
/* 205 */       cuantos++;
/*     */     } 
/* 207 */     arr.clear();
/*     */     
/* 209 */     Element elemento = this.pagHTML.getElementRGrupo();
/* 210 */     elemento.getParentNode().removeChild(elemento);
/* 211 */     return cuantos;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 215 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 216 */     atrib.setValue(valor);
/* 217 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido, boolean alinear, int colspan) {
/* 229 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 230 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 231 */     if (alinear) {
/* 232 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 234 */     if (colspan > 0) {
/* 235 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/*     */     
/* 238 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 239 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, int colspan) {
/* 243 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 244 */     if (colspan > 0) {
/* 245 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 247 */     Element enlace = this.pagHTML.createElement("a");
/* 248 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 249 */     enlace.appendChild(hijo);
/* 250 */     Attr donde = this.pagHTML.createAttribute("href");
/* 251 */     donde.setValue(vinculo);
/* 252 */     enlace.setAttributeNode(donde);
/* 253 */     td.appendChild(enlace);
/* 254 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 255 */     return td;
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
/*     */   private Collection crearSentencia(String fechaInicio, String fechaFin, int rol, int area, int estado, int servicio, int area2, int persona) {
/* 281 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 283 */     Collection arrDatos = rs.reporte01F4(servicio, estado, rol, fechaInicio, fechaFin, area, area2, persona);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     rs.close();
/* 294 */     return arrDatos;
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
/*     */   private Collection crearSentenciaGrupo(String fechaInicio, String fechaFin, int rol, int area, int estado, int servicio, int area2, int persona) {
/* 320 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 322 */     Collection arrDatos = rs.reporte01F4Grupo(fechaInicio, fechaFin, rol, area, estado, servicio, area2, persona);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 332 */     rs.close();
/* 333 */     return arrDatos;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Reporte01F4.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */