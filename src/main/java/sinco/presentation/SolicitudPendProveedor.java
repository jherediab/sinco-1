/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.regex.Pattern;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.SolicitudDTO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.presentation.SolicitudPendProveedor;
/*     */ import sinco.presentation.SolicitudPendProveedorHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class SolicitudPendProveedor
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudPendProveedorHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  37 */     int solicitud = 0;
/*     */     try {
/*  39 */       solicitud = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */     }
/*  41 */     catch (Exception e) {}
/*     */     
/*  43 */     int solPadre = 0;
/*     */     try {
/*  45 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*     */     }
/*  47 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  50 */     String _operacion = comms.request.getParameter("_operacion");
/*  51 */     if (_operacion == null || _operacion.length() == 0) {
/*  52 */       _operacion = "P";
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("C")) {
/*  56 */       creacion(comms, solicitud);
/*     */     }
/*     */     
/*  59 */     this.pagHTML = (SolicitudPendProveedorHTML)comms.xmlcFactory.create(SolicitudPendProveedorHTML.class);
/*  60 */     this.pagHTML.getElementSolicitud().setValue("" + solicitud);
/*  61 */     this.pagHTML.getElementSolPadre().setValue("" + solPadre);
/*     */     
/*  63 */     incluirPersonas(solicitud);
/*     */ 
/*     */     
/*  66 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  67 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private boolean incluirPersonas(int solicitud) {
/*  80 */     int posibleProveedor = 0;
/*  81 */     SolicitudDAO rsSol = new SolicitudDAO();
/*     */     
/*  83 */     SolicitudDTO regSol = rsSol.getSolicitud(solicitud);
/*  84 */     if (regSol.getNumeroMacroservicio() > 0) {
/*  85 */       regSol = rsSol.getSolicitud(regSol.getNumeroMacroservicio());
/*  86 */       if (regSol != null) {
/*  87 */         posibleProveedor = regSol.getEmpleadoCliente();
/*     */       }
/*     */     } 
/*  90 */     rsSol.close();
/*     */ 
/*     */     
/*  93 */     HTMLTableSectionElement hte = this.pagHTML.getElementPersonas();
/*     */     
/*  95 */     SisUsuariosDAO rs = new SisUsuariosDAO();
/*  96 */     Collection arr = rs.cargarDeServicio(solicitud);
/*     */     
/*  98 */     Iterator iterator = arr.iterator();
/*  99 */     boolean fondo = true;
/* 100 */     while (iterator.hasNext()) {
/* 101 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/*     */       
/* 103 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 105 */       fondo = !fondo;
/* 106 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 108 */       eltr.appendChild(newtd("" + reg.getApellidos() + " " + reg.getNombres()));
/* 109 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/*     */       
/* 111 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 113 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 114 */       checkbox.setAttribute("type", "checkbox");
/* 115 */       checkbox.setName("Fun_" + reg.getArea() + "_" + reg.getCodigoEmpleado());
/*     */       
/* 117 */       if (posibleProveedor == reg.getCodigoEmpleado()) {
/* 118 */         checkbox.setChecked(true);
/*     */       }
/*     */       
/* 121 */       tdMarca.appendChild(checkbox);
/* 122 */       eltr.appendChild(tdMarca);
/*     */       
/* 124 */       hte.appendChild(eltr);
/*     */     } 
/* 126 */     return true;
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
/*     */   private void creacion(HttpPresentationComms comms, int solicitud) throws HttpPresentationException, KeywordValueException {
/* 140 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 142 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/* 144 */     int solPadre = 0;
/*     */     try {
/* 146 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*     */     }
/* 148 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 151 */     int total = 0;
/*     */ 
/*     */ 
/*     */     
/* 155 */     while (enumera.hasMoreElements()) {
/* 156 */       String param = (String)enumera.nextElement();
/* 157 */       if (param.startsWith("Fun_")) {
/* 158 */         total++;
/*     */       }
/*     */     } 
/*     */     
/* 162 */     SolicitudDAO rs = new SolicitudDAO();
/* 163 */     int cuantas = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     enumera = comms.request.getParameterNames();
/* 169 */     while (enumera.hasMoreElements()) {
/* 170 */       String param = (String)enumera.nextElement();
/* 171 */       if (param.startsWith("Fun_")) {
/* 172 */         Pattern p = Pattern.compile("_");
/*     */         
/* 174 */         String[] arregloDatos = p.split(param);
/* 175 */         int areaResponsable = Integer.parseInt(arregloDatos[1]);
/* 176 */         int nuevoResponsable = Integer.parseInt(arregloDatos[2]);
/*     */         
/* 178 */         cuantas++;
/*     */         
/* 180 */         if (cuantas == total) {
/* 181 */           rs.asignarResponsable(solicitud, nuevoResponsable, areaResponsable, elUsuario);
/*     */           continue;
/*     */         } 
/* 184 */         rs.replicarSolicitud(solicitud, nuevoResponsable, areaResponsable, elUsuario);
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     rs.close();
/*     */     
/* 190 */     String sPagina = "VSPorAt.po?solicitud=" + solPadre;
/* 191 */     if (total == 1) {
/* 192 */       sPagina = "VerSolicitudNoEnviada.po?solicitud=" + solicitud + "&solPadre=" + solPadre;
/*     */     }
/* 194 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 208 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 209 */     atrib.setValue(valor);
/* 210 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido) {
/* 223 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 224 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 225 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 226 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudPendProveedor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */