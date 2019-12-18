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
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesEscalaronFuncionarios;
/*     */ import sinco.presentation.SolicitudesEscalaronFuncionariosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ public class SolicitudesEscalaronFuncionarios
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesEscalaronFuncionariosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  34 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */     
/*  38 */     String _operacion = comms.request.getParameter("_operacion");
/*  39 */     if (_operacion == null || _operacion.length() == 0) {
/*  40 */       _operacion = "L";
/*     */     }
/*     */ 
/*     */     
/*  44 */     if (_operacion.equals("C")) {
/*  45 */       atencion(comms);
/*     */     }
/*     */ 
/*     */     
/*  49 */     this.pagHTML = (SolicitudesEscalaronFuncionariosHTML)comms.xmlcFactory.create(SolicitudesEscalaronFuncionariosHTML.class);
/*  50 */     listar(comms);
/*     */     
/*  52 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  53 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void atencion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  69 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  70 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  72 */     Enumeration enumera = comms.request.getParameterNames();
/*  73 */     int cuantas = 0;
/*     */ 
/*     */     
/*  76 */     while (enumera.hasMoreElements()) {
/*  77 */       String param = (String)enumera.nextElement();
/*  78 */       if (param.startsWith("SOL_")) {
/*     */         try {
/*  80 */           String cadena = comms.request.getParameter(param);
/*  81 */           if (cadena == null) cadena = ""; 
/*  82 */           if (cadena.length() > 0) {
/*  83 */             int solicitud = Integer.parseInt(param.substring(4));
/*     */ 
/*     */             
/*  86 */             VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/*  87 */             VSolicitudesDTO regSol = rsVSol.getSolicitud(solicitud);
/*  88 */             rsVSol.close();
/*     */             
/*  90 */             if (regSol != null) {
/*  91 */               Varios oVarios = new Varios();
/*  92 */               oVarios.recordarCalificacion(2, idNav, regSol, elUsuario);
/*  93 */               cuantas++;
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*  99 */         catch (Exception e) {
/* 100 */           Utilidades.writeError("Recordar Atencion Multiple ", e);
/*     */         } 
/*     */       }
/*     */     } 
/* 104 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesEscalaronFuncionarios.po?mensaje=Operacion realizada en " + cuantas + " solicitudes"));
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
/*     */   private void listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 118 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 119 */     String mensaje = comms.request.getParameter("mensaje");
/*     */     
/* 121 */     if (mensaje != null) {
/* 122 */       this.pagHTML.setTextMensaje("" + mensaje);
/*     */     }
/*     */     
/* 125 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/* 126 */     Collection<VSolicitudesDTO> arr = rsSol.cargarEnEscalamientoPara(idNav);
/* 127 */     rsSol.close();
/*     */     
/* 129 */     boolean fondo = true;
/* 130 */     int cuantas = 0;
/* 131 */     HTMLTableSectionElement hte = this.pagHTML.getElementSolicitudes();
/* 132 */     Iterator<VSolicitudesDTO> iterator = arr.iterator();
/*     */     
/* 134 */     while (iterator.hasNext()) {
/* 135 */       VSolicitudesDTO regSol = (VSolicitudesDTO)iterator.next();
/*     */       
/* 137 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 139 */       fondo = !fondo;
/* 140 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 142 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/* 143 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero();
/*     */       
/* 145 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/* 146 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/* 147 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/* 148 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/* 149 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*     */ 
/*     */       
/* 152 */       HTMLElement tdEstado = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 154 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 155 */       checkbox.setAttribute("type", "checkbox");
/* 156 */       checkbox.setName("SOL_" + regSol.getNumero());
/* 157 */       checkbox.setId("" + regSol.getNumero());
/* 158 */       tdEstado.appendChild(checkbox);
/*     */       
/* 160 */       eltr.appendChild(tdEstado);
/*     */       
/* 162 */       hte.appendChild(eltr);
/*     */ 
/*     */       
/* 165 */       hte.appendChild(eltr);
/* 166 */       cuantas++;
/*     */     } 
/* 168 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 169 */     arr.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 174 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 175 */     atrib.setValue(valor);
/* 176 */     return atrib;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 180 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 181 */     Element enlace = this.pagHTML.createElement("a");
/* 182 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 183 */     enlace.appendChild(hijo);
/* 184 */     Attr donde = this.pagHTML.createAttribute("href");
/* 185 */     donde.setValue(vinculo);
/* 186 */     enlace.setAttributeNode(donde);
/* 187 */     td.appendChild(enlace);
/* 188 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 189 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 193 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 194 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 195 */     if (alinear) {
/* 196 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 198 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 199 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesEscalaronFuncionarios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */