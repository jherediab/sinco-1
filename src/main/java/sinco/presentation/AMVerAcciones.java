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
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.AMVerAcciones;
/*     */ import sinco.presentation.AMVerAccionesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMVerAcciones
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMVerAccionesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  43 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*  44 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  46 */     int estado = 0;
/*     */     try {
/*  48 */       estado = Integer.parseInt(comms.request.getParameter("estado"));
/*     */     }
/*  50 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  53 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  54 */     SisUsuariosDTO p = pf.cargarRegistro(idNav);
/*     */     
/*  56 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  57 */     if (!rsSeguridad.getEstadoConexion()) {
/*  58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  60 */     boolean permisoCalificarEnArea = rsSeguridad.tieneLlave(miGrupo, "AM_PermisoCalificarEnArea");
/*  61 */     boolean permisoCalificarGeneral = rsSeguridad.tieneLlave(miGrupo, "AM_PermisoCalificarGeneral");
/*     */     
/*  63 */     rsSeguridad.close();
/*     */     
/*  65 */     this.pagHTML = (AMVerAccionesHTML)comms.xmlcFactory.create(AMVerAccionesHTML.class);
/*     */     
/*  67 */     switch (estado) {
/*     */       case 0:
/*  69 */         this.pagHTML.setTextTitulo("no enviadas");
/*     */         break;
/*     */       case 1:
/*     */       case 11:
/*  73 */         this.pagHTML.setTextTitulo("Seguimiento");
/*     */         break;
/*     */       case 2:
/*  76 */         this.pagHTML.setTextTitulo("Implantadas");
/*     */         break;
/*     */       case 4:
/*  79 */         this.pagHTML.setTextTitulo("Anuladas");
/*     */         break;
/*     */       case 5:
/*  82 */         this.pagHTML.setTextTitulo("Por calificar");
/*     */         break;
/*     */     } 
/*     */     
/*  86 */     AreasDAO areaf = new AreasDAO();
/*  87 */     AreasDTO regArea = areaf.getArea(p.getArea());
/*  88 */     areaf.close();
/*     */     
/*  90 */     SisUsuariosDAO ps = new SisUsuariosDAO();
/*  91 */     SisUsuariosDTO jefeArea = ps.getJefeArea(miArea);
/*     */     
/*  93 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/*  94 */     int area = 0;
/*     */     
/*  96 */     if (estado == 5 && permisoCalificarGeneral) {
/*  97 */       area = -1;
/*     */     }
/*  99 */     else if (estado == 5 && permisoCalificarEnArea) {
/* 100 */       area = p.getArea();
/*     */     }
/* 102 */     else if (estado == 2) {
/* 103 */       area = p.getArea();
/*     */     } 
/*     */     
/* 106 */     Collection arr = new ArrayList();
/* 107 */     if (estado != 11) {
/* 108 */       if (estado == 1) {
/* 109 */         arr = rsAcciones.cargarAccionesPorCausa(idNav, 1);
/*     */       } else {
/*     */         
/* 112 */         arr = rsAcciones.cargarAccionesDe(idNav, miArea, (jefeArea != null) ? jefeArea.getCodigoEmpleado() : 0, idNav, estado, area, regArea.getSecuencia());
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 123 */       arr = rsAcciones.cargarAccionesDe(idNav, miArea, (jefeArea != null) ? jefeArea.getCodigoEmpleado() : 0, idNav, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 131 */     boolean fondo = true;
/* 132 */     Iterator iterator = arr.iterator();
/* 133 */     while (iterator.hasNext()) {
/* 134 */       AMAccionesDTO reg = (AMAccionesDTO)iterator.next();
/*     */       
/* 136 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 137 */       fondo = !fondo;
/* 138 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 140 */       String pagina = "AMDetalleV2.po?numero=" + reg.getNumero();
/* 141 */       eltr.appendChild(newtdhref("" + reg.getNumero(), pagina));
/*     */       
/* 143 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaGenerada())));
/* 144 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 145 */       eltr.appendChild(newtd("" + reg.getTemaAccion()));
/* 146 */       hte.appendChild(eltr);
/*     */     } 
/* 148 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 149 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 164 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 165 */     atrib.setValue(valor);
/* 166 */     return atrib;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 201 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 202 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 203 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 204 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMVerAcciones.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */