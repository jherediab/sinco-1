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
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TipoSolicitudDTO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TipoSolicitudDAO;
/*     */ import sinco.presentation.VerTipoSolicitud;
/*     */ import sinco.presentation.VerTipoSolicitudHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class VerTipoSolicitud
/*     */   implements HttpPresentation
/*     */ {
/*     */   private VerTipoSolicitudHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  33 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  35 */     this.pagHTML = (VerTipoSolicitudHTML)comms.xmlcFactory.create(VerTipoSolicitudHTML.class);
/*  36 */     TipoSolicitudDAO tsf = new TipoSolicitudDAO();
/*  37 */     tsf.cargarTodos();
/*     */     
/*  39 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  40 */     TipoSolicitudDTO tsol = tsf.next();
/*     */     
/*  42 */     boolean fondo = false;
/*  43 */     while (tsol != null) {
/*  44 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  45 */       fondo = !fondo;
/*  46 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  48 */       String sPagina = "TASetTipo.po?tipoSol=" + tsol.getCodigo();
/*  49 */       eltr.appendChild(newtdhref("" + tsol.getDescripcion(), sPagina));
/*     */       
/*  51 */       hte.appendChild(eltr);
/*     */       
/*  53 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  54 */       fondo = !fondo;
/*  55 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  57 */       eltr.appendChild(newtd("."));
/*  58 */       hte.appendChild(eltr);
/*     */       
/*  60 */       tsol = tsf.next();
/*     */     } 
/*  62 */     tsf.close();
/*     */     
/*  64 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  65 */     SisUsuariosDTO p = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */     
/*  67 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  68 */     if (!rsSeguridad.getEstadoConexion()) {
/*  69 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  71 */     if (!rsSeguridad.tieneLlave(miGrupo, "HelpDesk")) {
/*  72 */       HTMLElement sel = this.pagHTML.getElementHelpDesk();
/*  73 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  75 */     rsSeguridad.close();
/*     */     
/*  77 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  78 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  82 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  83 */     atrib.setValue(valor);
/*  84 */     return atrib;
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
/*  96 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  97 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  98 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  99 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 103 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 104 */     Element negrita = this.pagHTML.createElement("b");
/*     */     
/* 106 */     Element enlace = this.pagHTML.createElement("a");
/* 107 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 108 */     enlace.appendChild(hijo);
/* 109 */     Attr donde = this.pagHTML.createAttribute("href");
/* 110 */     donde.setValue(vinculo);
/* 111 */     enlace.setAttributeNode(donde);
/* 112 */     negrita.appendChild(enlace);
/*     */     
/* 114 */     td.setAttributeNode(newAttr("valign", "top"));
/*     */     
/* 116 */     td.appendChild(negrita);
/*     */     
/* 118 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 119 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerTipoSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */