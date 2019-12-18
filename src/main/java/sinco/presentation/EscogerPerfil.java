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
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.EscogerPerfil;
/*     */ import sinco.presentation.EscogerPerfilHTML;
/*     */ import sinco.spec.MenuFactory;
/*     */ import sinco.spec.UserDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EscogerPerfil
/*     */   implements HttpPresentation
/*     */ {
/*     */   private EscogerPerfilHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion&texto=Su sesion no ha sido iniciada o ha caducado. Regresa a la pagina principal e ingrese su login y password de nuevo"));
/*     */     }
/*     */     
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  37 */     String _operacion = comms.request.getParameter("_operacion");
/*  38 */     if (_operacion == null) _operacion = "L";
/*     */     
/*  40 */     if (_operacion.equals("P")) {
/*  41 */       int area = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */ 
/*     */       
/*  44 */       SisUsuariosDAO pf = new SisUsuariosDAO();
/*  45 */       SisUsuariosDTO regPersona = pf.cargarRegistro(idNav);
/*     */       
/*  47 */       PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/*  48 */       PersonasAreaDTO regPerfil = rsPersonasArea.cargarRegistro(area, idNav);
/*  49 */       int miClase = regPerfil.getClase();
/*  50 */       rsPersonasArea.actualizarAreaPersona(idNav, regPerfil.getCodigoArea(), miClase, regPersona.getIdcorreo());
/*  51 */       comms.session.getSessionData().set("miGrupo", "" + miClase);
/*  52 */       comms.session.getSessionData().set("miArea", "" + regPerfil.getCodigoArea());
/*  53 */       rsPersonasArea.close();
/*     */       
/*  55 */       MenuFactory miMenu = new MenuFactory();
/*  56 */       if (!miMenu.crearMenu("" + regPersona.getCodigoEmpleado(), miClase, "SOLICITUDES")) {
/*  57 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorConstruccionMenu"));
/*     */       }
/*     */       
/*  60 */       UserDO miUser = new UserDO(regPersona.getIdcorreo());
/*     */       
/*  62 */       comms.session.setUser(miUser);
/*     */       
/*  64 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("WelcomePresentation.po"));
/*     */     } 
/*     */     
/*  67 */     this.pagHTML = (EscogerPerfilHTML)comms.xmlcFactory.create(EscogerPerfilHTML.class);
/*  68 */     PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/*  69 */     if (!rsPersonasArea.getEstadoConexion()) {
/*  70 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  72 */     rsPersonasArea.cargarDePersona(idNav);
/*  73 */     PersonasAreaDTO reg = rsPersonasArea.next();
/*  74 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  75 */     boolean fondo = true;
/*  76 */     while (reg != null) {
/*  77 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  78 */       fondo = !fondo;
/*  79 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*  80 */       eltr.appendChild(newtdhref("" + reg.getNombreArea(), "EscogerPerfil.po?_operacion=P&codigoArea=" + reg.getCodigoArea() + "&clase=" + reg.getClase()));
/*  81 */       eltr.appendChild(newtd("" + reg.getNombreClase()));
/*  82 */       hte.appendChild(eltr);
/*  83 */       reg = rsPersonasArea.next();
/*     */     } 
/*  85 */     rsPersonasArea.close();
/*     */     
/*  87 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 100 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 101 */     atrib.setValue(valor);
/* 102 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 114 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 115 */     Element enlace = this.pagHTML.createElement("a");
/* 116 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 117 */     enlace.appendChild(hijo);
/* 118 */     Attr donde = this.pagHTML.createAttribute("href");
/* 119 */     donde.setValue(vinculo);
/* 120 */     enlace.setAttributeNode(donde);
/* 121 */     td.appendChild(enlace);
/* 122 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 132 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 133 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 134 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerPerfil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */